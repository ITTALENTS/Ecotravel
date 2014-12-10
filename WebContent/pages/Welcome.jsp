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
		<form id="loginForm" method="Post" >
			<div>
				<label for="username">Username:</label>
				<input type="text" name="username" placeholder="Enter your username" required="required"/>
			</div>
			
			<div>
				<label for="password">Password:</label>
				<input type="password" name="password" placeholder="Enter your password" required="required"/>
			</div>
			<div>
				<input type="submit" name="submitButton" value="Submit">
			</div>
		</form>
			<a id="forgottenPassword" href="pages/ForgottenPassword.jsp">Forgotten password</a>
			<a id="registerForm" href="pages/RegisterForm.jsp">Create new account</a>
			
		<% 
			if(request.getAttribute("error_login_message") != null) {
		%>
		<div><h3><%
				out.print(request.getAttribute("error_login_message"));
			}
		%>
		</h3></div>
		
		<!-- or we could use this <h3> ${error_login_message} </h3> -->
		
			 
			 	
	</div>
</body>
</html>