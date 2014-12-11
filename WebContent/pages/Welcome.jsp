<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/welcomeStyle.css">
</head>
<body>
	<div id="loginWrapper">
		<%@ include file="Header.jsp" %>
		<form id="loginForm" method="POST" >
			<div>
				<label for="username">Username:</label>
				<input type="text" name="username" placeholder=" Enter your username" required="required"/>
			</div>
			
			<div>
				<label for="password">Password:</label>
				<input type="password" name="password" placeholder=" Enter your password" required="required"/>
			</div>
			<div>
				<input type="submit" name="submitButton" value="Submit">
			</div>
		</form>
			<a id="forgottenPassword" href="ForgottenPassword">Forgotten password</a>
			<a id="registerForm" href="RegisterForm">Create new account</a>
			
		
		<h3 style="color:red"> ${login_error} </h3>
		<h4>${reg_complete_msg}</h4>
			 	
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>