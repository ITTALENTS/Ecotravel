<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Road trip</title>
</head>
<body>
	<%@ include file="Header.jsp"%> 
	<div id="welcome" class="container">
		<form id="forgottenPassword" method="POST" class="form-horizontal">
			<div class="form-group">
			
				<label for="email" class="col-md-2 control-label">Your username:</label> 
				<div class="col-md-10">
					<input type="text" name="username" placeholder="Enter your username" required="required" class="form-control"/>
				</div>
				<input type="submit" name="submitButton" value="Send new password" class="btn btn-default">
			</div>
		</form>
		<h3>${generatingPasswordStatus}</h3>
		<a id="loginForm" href="Welcome" class="text-success">Login</a>	
		<a id="registerForm" href="RegisterForm" class="text-warning">Create new account</a>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>