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
	<%@ include file="Header.jsp" %>
	<div id="welcome" class="container">
		<form id="loginForm" method="POST" class="fom-horizontal" action="Login">
			<div class="form-group">
				<label for="username" class="col-md-2 control-label">Username:</label>
				<div class="col-md-10">
					<input id="username" type="text" class="form-control control-field" name="username" placeholder=" Enter your username" required="required"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="password" class="col-md-2 control-label">Password:</label>
				<div class="col-md-10">
					<input id="password" type="password" class="form-control control-field" name="password" placeholder=" Enter your password" required="required"/>
				</div>
			</div>
			<div>
				<input type="submit" name="submitButton" value="Submit" class="btn btn-success">
			</div>
		</form>
			<a id="forgottenPassword" href="ForgottenPassword" class="text-danger">Forgotten password</a>
			<a id="registerForm" href="RegisterForm" class="text-info">Create new account</a>
			
		
		<h3 style="color:red"> ${login_error} </h3>
		<h4>${reg_complete_msg}</h4>
			 	
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>