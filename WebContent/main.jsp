<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
		window.onload=function(){
			
	//		alert("验证加载弹框");
			
		}
</script>
	<br>

<form action="login.form" method="post"  style="text-align:center">
	username:<input type="text" name="name" ><br/>
	<br>
	password:<input type="text" name="pwd"><br>
	<center>${message }</center>
	<br>
	  &emsp;  &emsp; &emsp;  &nbsp;   <input type="submit" value="登录">	  
</form>
	<form action="http://test.cyprexsvc.fir.ai/account/user/judge/register/" method="post"  style="text-align:center">
	类型:<input id="mob" type="text" name="key"  readonly="readonly" value="mobile" ><br/>
	<br>
	手机号码:<input id="mobs" type="text" name="vale"><br>
	<center>${message }</center>
	<br>
	  &emsp;  &emsp; &emsp;  &nbsp;  <button id="bu" onclick="re()">验证是否注册</button>	<br/> 
	  
	  <div id="sc"></div>
</form>
	<p id="cc">
		<button onclick="re1()">验证是否注册</button>
	</p>
	<script type="text/javascript">
		function re1(){
			var key=$("#mob").val();
			var value=$("#mobs").val();
			var da="";
			$.ajax({
				type: "post",
				dataType: "json",
				url: "http://test.cyprexsvc.fir.ai/account/user/judge/register/",
				data: {"key":key,"value":value},
				success: function(data){
				da=JSON.stringify(data);
					//alert(da);
					$("#sc").html(da);
					
				}
				
			});
			
		}
	
	</script>
	
</body>
</html>