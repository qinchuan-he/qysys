<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<title>修改接口</title>
</head>
<body>
<div>
	<button onclick="goback()">返回</button>
</div>
	<div >
		<form action="modifyinter.action" method="post">
		<input type="hidden" name="id" value="${map.id }"  >
		<table border="1px" width="80%" >
			<tr>
				<td>环境名称</td>
					<td>
				      <select name="sysid" id="sysone" >
							<option id="systwo"  value="${map.sysid }">${map.systemname }</option>
							<c:forEach items="${syslist }" var="sys">
								<option value="${sys.id }">${sys.systemname }</option>
							</c:forEach>
					  </select>
								  
					</td>
				<td>接口名</td><td><input type="text" name="inname" value="${map.inname }"> </td>
			</tr>
			<tr>
				<td>url</td><td><input type="text" name="url" value="${map.url }"> </td>
				<td>请求方式</td><td>
									<select name="method">
										<option value="${map.method }">${map.method }</option>
										<c:if test="${map.method=='post' }">
										<option value="get">get</option>
										</c:if>
										<c:if test="${map.method=='get' }">
										<option value="post">post</option>
										</c:if>	
										<c:if test="${map.method eq null }">
											<option value="post">post</option>
											<option value="get">get</option>
										</c:if>									
									</select>
				</td>
				
			</tr>
			<tr>
				<td>描述</td><td><input type="text" name="des" value="${map.des }"> </td>
				<td>响应断言</td><td><input name="check" value="${map.check }"></td>
			</tr>
			<tr >
				<td width="10%">请求参数</td><td colspan="3" ><textarea name="param" cols="90%" rows="20%">${map.param }</textarea></td>
			</tr>			
		</table>
		<input type="reset" value="重置">
		<input type="submit" value="保存">
			</form>
	</div>
	

	
	<script type="text/javascript">
		function goback(){
				window.history.go(-1);
			}	
		
	
		
		
	
		
	</script>
</body>
</html>