<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="loginWrapper">
		<form id="loginForm" method="Post" action="Welcome.html">
			<div>
				<label for="username">Username:</label>
				<input type="text" name="username" value="Enter username" required="required"/>
			</div>
			
			<div>
				<label for="password">Password:</label>
				<input type="password" name="password" value="Enter password" required="required"/>
			</div>
			<input type="submit" name="submitButton" value="Submit">
		</form>
			<a id="forgottenPassword" href="/pages/ForgottenPassword.jsp">Forgotten password</a>
			<a id="registerForm" href="/pages/RegisterForm.jsp">Create new account</a>
			 
			 	
	</div>
</body>
</html>