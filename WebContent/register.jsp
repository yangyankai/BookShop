<%@ page language="java" contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>BaseProgram</title>
</head>
<style> 
body{ text-align:center} 
</style> 
<body background="./img/background.jpg">

	<form action="./register_action.jsp" method="post">
		提交<input type="text" name="username" /> <br> 
		密码<input	type="password" name="password" /> <br> 
		邮箱<input type="text"	name="email" /> <br>
		<button type="提交">submit</button>
	</form>
</body>
</html>