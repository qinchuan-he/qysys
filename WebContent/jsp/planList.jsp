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
<title>计划列表</title>
</head>
<body>
	<div>
		<button onclick="goback()">返回</button>
	</div>
	<div style="text-align: center;">
		输入计划名称：<input id="selectName" name="pname" type="text"> &emsp;
		<button onclick="selectPlan()">查询</button>&emsp;
		<a href="selectAddPlan.action"><button>新增</button></a>
		<table id="tab" border="1px" width="80%">
			<tr id="one"><td>计划名称</td><td>描述</td><td>修改时间</td><td>操作</td><td>执行结果</td></tr>
			<c:forEach items="${list }" var="plan">
				<tr><td>${plan.pname }</td><td>${plan.des }</td><td>${plan.updatetime }</td><td>
				<a href="plandetail.action?id=${plan.id }">详情</a>&ensp;
				<a href="modifyselect.action?id=${plan.id }">修改</a>&ensp;
				<a href="#" id="${plan.id }" onclick="deletePlan(this)">删除</a>&ensp;
				<a href="#" id="${plan.id }" onclick="execute(this)">执行</a>
				</td><td><a href="showresult.action?pid=${plan.id }">查看执行结果</a></td></tr>
			</c:forEach>
			
		</table>
	</div>
	<div id="fir" style="text-align: center;"></div>
	
	
	<script type="text/javascript">
		function goback(){
			window.history.go(-1);
		}
		function selectPlan(){
			var name=$("#selectName").val();
			$.ajax({
				url: "selectPlanName.action",
				type: "post",
				dataType: "json",
				data: {"name":name},
				success:function(da){
					//alert(da);
					//var lis=JSON.stringify(da);
					//alert(lis);
					var data=da["list"];
					$("#one~tr").remove();
					for(var i in data){
					$("#tab").append("<tr><td>"+data[i].pname+"</td><td>"+data[i].des+"</td><td>"+data[i].updatetime+"</td><td>"
				+"<a href=plandetail.action?id="+data[i].id+">详情</a>&emsp;"
				+"<a href=modifyselect.action?id="+data[i].id+">修改</a>&emsp;"
				+"<a href=# id="+data[i].id+" onclick=deletePlan(this)>删除</a>&emsp;"
				+"<a href=# id="+data[i].id+" onclick=execute(this)>执行</a>"
				+"</td><td><a href=showresult.action?pid="+data[i].id+">查看执行结果</a></td></tr>");
					}
				}
			});
		}
		function deletePlan(obj){
			var id=$(obj).attr("id");
			$.ajax({
				url: "deletePlan.action",
				type: "post",
				dataType: "json",
				data: {"id":id},
				success:function(data){
					$(obj).remove();
				}
			});
		}
		
		function execute(obj){
			var pid=$(obj).attr("id");
			var n=0;
			$.ajax({
				url: "planExecute.action",
				type: "post",
				async: false,
				dataType: "json",
				data: {"id":pid},
				success: function(data){
					//alert(data["list"]);
					var cc=data["list"];
					var exenum=data["exenum"];
				for(var i in cc){
				var uri=cc[i].url;
				var apiid=cc[i].interid;
				var method=cc[i].method;
				var num=cc[i].num;
				//alert(num);
				if(method=="post"){
				var s=cc[i].param;
				var ss=JSON.parse(s);
				
				$.ajax({				//调用接口
						url: uri,
						type: method,
						dataType: "json",
						async: false,
						xhrFields:{ // 访问接口跨域请求用 
							withCredentials:true
						},
						data: ss,
						success: function(obj){
							
							var da=JSON.stringify(obj);
							//$("#fir").html(da);
							var status=obj["status"];
							//$("#fir").append("<div>"+da+"</div>");
								$.ajax({			//把接口返回结果存入数据库
									url: "addresponse.action",
									type: "post",
									dataType: "json",
									data: {"pid":pid,"apiid":apiid,"method":method,"result":status,"respon":da,"num":num,"exenum":exenum},
									async: false,
									xhrFields: {withCredentials: true},
									success: function(res){
										$("#fir").append("<div>"+da+"</div>");
									}
								});
							
				}
			});
				}else{
				$.ajax({
						url: uri,
						type: method,
						dataType: "json",
						async: false,
						xhrFields:{ // 访问接口跨域请求用 
							withCredentials:true
						},
						success: function(obj){						
							var da=JSON.stringify(obj);
							//$("#fir").html(da);
							var status=obj["status"];
							//$("#fir").html(da);
						//	$("#fir").append("<div>"+da+"</div>");
								$.ajax({			//把接口返回结果存入数据库
									url: "addresponse.action",
									type: "post",
									dataType: "json",
									data: {"pid":pid,"apiid":apiid,"method":method,"result":status,"respon":da,"num":num,"exenum":exenum},
									async: false,
									xhrFields: {withCredentials: true},
									success: function(res){
										$("#fir").append("<div>"+da+"</div>");
									}
								});							
				}
			});		
				}
					}	
					$("#fir").append("<div>"+"--------------执行完成----------------"+"</div>");
					}
				
				//}
			});
		}
		
	</script>
</body>
</html>