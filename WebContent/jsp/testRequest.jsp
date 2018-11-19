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
<title>测试请求</title>
</head>
<body>
<button onclick="goback()">返回</button><br>
<div>
	<button onclick="send()">屠龙刀点击就送</button><!-- 这是一个写死的方法，验证用 -->
</div>
<div id="sc" style="text-align: center;"></div>
<div style="text-align: right;">
	
</div>
<div>
	<table border="1px" width="90%">
		<tr id="one">
			<td width="5%">环境名称</td><td width="25%">${map.systemname }</td>
			<td width="5%">环境地址</td><td width="25%" id="urlname">${map.urlname }</td>
			<td width="5%">接口名</td><td width="25%">${map.inname }</td>
		</tr>
		<tr id="two">
			<td >URL</td><td id="url">${map.url }</td>
			<td >请求方法</td><td id="method">${map.method }</td>
			<td >描述</td><td >${map.des }</td>
		</tr>
		<tr>
			<td >更新时间</td><td>${map.updatetime }</td>
			<td >响应断言</td><td id="check" value="${map.check }">${map.check }</td>
			<td >操作</td><td align="center"><button onclick="sendRequest()">执行</button></td>
		</tr>
		<tr>
			<td   >请求参数</td><td rowspan="5" colspan="5" id="param" >${map.param }</td>
		</tr>
	</table>
</div>
<div style="text-align: center;">
	<table border="1px" width="90%" height="200px">

				<tr>
			<td id="responsecontent" ></td>
		</tr>
	</table>
</div>	
	<script type="text/javascript">
		function sendRequest(){
			var a=$("#urlname").html();//获取域名
			var b=$("#url").html();//获取请求地址（不含有域名）
			var url=a+b; // 组合成请求地址，必须加上http://(https请求加https://)，否则将加上本项目的请求前缀
			var method=$("#method").html();
			var param=$("#param").html();//这里获取的是json格式的字符串，并不是json
			var request=JSON.parse(param);//这里需要将参数转换成json对象，否则参数会报错
			$.ajax({
				url: url,//"http://test.cyprexsvc.fir.ai/account/user/judge/register/",
				type: method,
				dataType: "json",
				data: request, //{"key":"mobile","value":19965632653},
				xhrFields:{ // 访问接口跨域请求用 
					withCredentials:true
				},
				success: function(obj){
					var result=JSON.stringify(obj);//将返回的json对象解析成字符串
					$("#responsecontent").html(result);
				}							
			});
		}
	
		function send(){
			$.ajax({
				url: "http://test.cyprexsvc.fir.ai/account/user/judge/register/",
				type: "post",
				dataType: "json",
				data: {"key":"mobile","value":19965632653},
				success: function(obj){
					da=JSON.stringify(obj);
					$("#sc").html(da);
					
				}
			});
		}
		
		function goback(){
			window.history.go(-1);
		}

		
		
		
	</script>
</body>
</html>