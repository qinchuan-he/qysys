<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<title>计划详情</title>
</head>
<body>
<div>
	<button onclick="goback()">返回</button>
</div>
<div style="text-align: center;">
	<table id="tab1" border="1px" width="80%">
	
			<input id="planid" type="hidden" value="${list.id }">
			<tr>
				<td>环境名称</td><td>${list.systemname }</td>
				<td>计划名称</td><td>${list.pname }</td>
			</tr>
			<tr>
				<td>技术描述</td><td>${list.des }</td>
				<td>更新时间</td><td>${list.updatetime }</td>
			</tr>
	</table>
	<input id="plugName" type="text">&emsp;<button id="${list.id }" onclick="selectPlug(this)">查询组件</button>&emsp;
	<input id="interfaceName" type="text">&emsp;<button id="${list.id }" onclick="selectInter(this)">查询接口</button>&emsp;
	<button id="${list.id }" onclick="selectplan(this)">计划详情</button>
	<table id="tab2" border="1px" width="80%">
		<tr id="one"><td>名字</td><td>描述</td><td>操作</td></tr>
		<c:forEach items="${detail }" var="d">
			<tr><td>${d.name }</td><td>${d.des }</td><td>
				<a href="#" type="${d.type }" id="${d.id }" onclick="detailPlan(this)">详情</a>
				<a href="#" type="${d.type }" id="${d.id }" onclick="deletePlanDetail(this)">删除</a>
			</td></tr>
		</c:forEach>
	</table>
</div>
</body>
	<script type="text/javascript">
		function goback(){
			window.history.go(-1);
		}
		
		function selectPlug(obj){
			var name=$("#plugName").val();
			var id=$(obj).attr("id");
			$.ajax({
				url: "selectAddPlug.action",
				type: "post",
				dataType: "json",
				data: {"id":id,"name":name},
				success: function(data){
				$("#one~tr").remove();
					for(var i in data){
						$("#tab2").append("<tr><td>"+data[i].plname+"</td><td>"+data[i].pldes+"</td><td>"
						+"<a href=# type=1 id="+data[i].id+" onclick=detailPlan(this)>详情</a>&emsp;"
						+"<a href=# type=1 id="+data[i].id+" onclick=planAdd(this)>添加</a>"
						+"</td></tr>");
					}
				}
			});
		}
		
		function selectInter(obj){
			var name=$("#interfaceName").val();
			var id=$(obj).attr("id");
			$.ajax({
				url: "selectAddInter.action",
				type: "post",
				dataType: "json",
				data: {"name":name,"id":id},
				success: function(data){
					$("#one~tr").remove();
					for(var i in data){
						$("#tab2").append("<tr><td>"+data[i].inname+"</td><td>"+data[i].des+"</td><td>"
						+"<a href=# type=0 id="+data[i].id+" onclick=detailPlan(this)>详情</a>&emsp;"
						+"<a href=# type=0 id="+data[i].id+" onclick=planAdd(this)>添加</a>"
						+"</td></tr>");
					}
				}
			});
			
		}
		
		function selectplan(obj){
			var id=$(obj).attr("id");
			$.ajax({
				url: "showPlan.action",
				type: "post",
				dataType: "json",
				data: {"id":id},
				success: function(data){
					$("#one~tr").remove();
					for(var i in data){				
						$("#tab2").append("<tr><td>"+data[i].name+"</td><td>"+data[i].des+"</td><td>"
						+"<a href=# type="+data[i].type+" id="+data[i].id+" onclick=detailPlan(this)>详情</a>&emsp;"
						+"<a href=# type="+data[i].type+" id="+data[i].id+" onclick=deletePlanDetail(this)>删除</a>"
						+"</td></tr>");
					}
				}
			});
		}
		
		function deletePlanDetail(obj){
			var pid=$("#planid").val();
			var id=$(obj).attr("id");
			var type=$(obj).attr("type");
			$.ajax({
				url: "deletePlanDetail.action",
				type: "post",
				dataType: "json",
				data: {"pid":pid,"id":id,"type":type},
				success: function(data){
					alert("删除成功");
					$(obj).remove();
				}
			});
		}
		
		function detailPlan(obj){
			var id=$(obj).attr("id");
			var type=$(obj).attr("type");
			$.ajax({
				url: "allDetail.action",
				type: "post",
				dataType: "json",
				data: {"id":id,"type":type},
				success: function(data){
					var r=data["result"];
					//window.location.href=data["result"];
					window.location.href="testinter.action?id="+id;
					if(r==="interface"){
						window.location.href="testinter.action?id="+id;
					}else if(r==="plug"){
						window.location.href="describeplug.action?id="+id;
					}else{
						alert("错误");
					}
					
					
				}
			});
		}
		
		function planAdd(obj){
			var pid=$("#planid").val();
			var id=$(obj).attr("id");
			var type=$(obj).attr("type");
			$.ajax({
				url: "planAdd.action",
				type: "post",
				dataType: "json",
				data: {"pid":pid,"id":id,"type":type},
				success: function(data){
					
					var result=JSON.stringify(data);
					var r=data["result"]
					if(r==1){
						alert("加入成功");
					}else{
						alert("已经加入");
					}
				}
			});
			
			
		}
		
		
		
	</script>
</html>