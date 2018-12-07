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
<title>组件详情</title>
</head>
<body>
<div>
	<button id="goback">返回</button>
	<button></button>
</div>
<div>
	<div>
		<table border="1px" width="80%">
			<tr>
				<td>组件名</td><td>${plug[0].plname }</td><td>组件描述</td><td>${plug[0].pldes }</td>
			</tr>
		</table>
	</div><br><br>
	<div style="text-align: center;">
					<input id="plid" type="hidden" value="${plug[0].id }">
		输入接口名称：<input id="search" type="text">&emsp;<button onclick="searchOtherInter()">查询</button>&emsp;
					<button  id="${plug[0].id }" onclick="searchPlugInter(this.id)">显示组件内接口</button>
	</div>
	<div>
		<table id="tab" border="1px" width="80%">
			<tr id="one"><td>接口名称</td><td>请求地址</td><td>请求类型</td><td>接口描述</td><td>操作</td></tr>
			<c:forEach items="${plinlist }" var="list">
				<tr><td>${list.inname }</td><td>${list.url }</td><td>${list.method }</td><td>${list.des }</td><td>
				<button id="${list.id }" onclick="deletePlugInter(this)">删除</button>
				</td></tr>
			</c:forEach>
			
		</table>
	</div>
</div>

<script type="text/javascript">
	$("#goback").click(function(){
			window.history.go(-1);
		});
		
		function searchOtherInter(){
			var name=$("#search").val();
			var id=$("#plid").val();
			$.ajax({
				url: "searchOtherInter.action",
				type: "post",
				dataType: "json",
				data: {"id":id,"name":name},
				success:function(obj){
					$("#one~tr").remove();
					for(var i in obj){
						$("#tab").append("<tr><td>"+obj[i].inname+"</td><td>"
						+obj[i].url+"</td><td>"
						+obj[i].method+"</td><td>"
						+obj[i].des+"</td><td>"
						+"<button id="+obj[i].id+" onclick="+"addPlugInter(this.id)"+">增加</button>"
						+"</td></tr>");
					}
				}
			});
		}
		
		function addPlugInter(inid){
			var pid=$("#plid").attr("value");
			$.ajax({
				url: "plugAddInter.action",
				type: "post",
				dataType: "json",
				data: {"pid":pid,"inid":inid},
				success:function(obj){
					var s=JSON.stringify(obj);
					alert(s);
				}				
			});
		}
		
		function searchPlugInter(pid){
			//var  id=$("#plid").val();
			$.ajax({
				url: "searchPlugInter.action",
				type: "post",
				//async: false,
				dataType: "json",
				data: {"id":pid},
				success:function(obj){
				$("#one~tr").remove();
					for(var i in obj){
						$("#tab").append("<tr><td>"+obj[i].inname+"</td><td>"
						+obj[i].url+"</td><td>"
						+obj[i].method+"</td><td>"
						+obj[i].des+"</td><td>"
						+"<button id="+obj[i].id+" onclick="+"deletePlugInter(this)"+">删除</button>"
						+"</td></tr>");
					}
				}
			});
		}
		
		
		function deletePlugInter(obj){
			var pid=$("#plid").attr("value");
			var inid=$(obj).attr("id");
			//alert(inid);
			$.ajax({
				url: "deletePlugInter.action",
				type: "post",
				dataType: "json",
				data: {"pid":pid,"inid":inid},
				success:function(data){
					var result=JSON.stringify(data);
					alert(result);
					$(obj).parent().parent().remove();
				}
			});
			
		}
		

		
</script>
</body>
</html>