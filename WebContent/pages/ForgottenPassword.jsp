<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	<form id="forgottenPassword" method="Post" action="#">
		<label for="email">Your email:</label>
		<input type="text" name="email" placeholder=" Enter your email" required="true"/>
		<input type="submit" name="submitButton" value="Send new password">
	</form>
	<a id="loginForm" href="Welcome">Login</a>	
	<a id="registerForm" href="RegisterForm">Create new account</a>
	<%@ include file="Footer.jsp" %>
</body>
</html>