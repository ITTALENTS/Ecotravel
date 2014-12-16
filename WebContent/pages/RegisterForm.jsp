<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- @ taglib prefix="sf" uri="http://www.springframework.org/tags/form" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Road trip</title>
</head>
<body>

	<%@ include file="Header.jsp" %>
	<div class="container">
		<form id="registerForm" method="POST" action="RegisterForm" class="form-horizontal">
			<div class="form-group" id="nameField">
				<label class="control-label" for="name">Name:</label>
				<input class="form-control" id="name" type = "text" name = "name" required="true"/>
			</div>
			
			<div class="form-group" id="age">
				<label class="control-label" for="birthYear">Birth Year:</label>
				<input class="form-control" id="birthYear" type="number" name="birthYear" min="1950" max="2000">
			</div>
			
			<div class="form-group" id="phoneField">
				<label class="control-label" for="telephone">Phone:</label>
				<input class="form-control" id="telephone" type = "tel" name = "telephone" required="true"/>
			</div>
			
			<div class="form-group" id="emailField">
				<label class="control-label" for="email">Email:</label>
				<input class="form-control" id="email"  type = "email" name = "email" required="true"/>
				<!-- name should be "profile.email" to to the mapping -->
			</div>
			<h4 style="color:red">${email_taken_msg}</h4>
			
			<div class="form-group" id="usernameFiled">
				<label class="control-label" for="username">Username:</label>
				<input id="username" class="form-control" type = "text" name = "username" required="true"/>
				<!-- name should be "profile.username" to to the mapping -->
			</div>
			<h4 style="color:red">${username_taken_msg}</h4>
			
			<div class="form-group" id="passwordField">
				<label class="control-label" for="password">Password:</label>
				<input id="password" class="form-control" type = "password" name = "password" required="true"/>
				<!-- name should be "profile.password" to to the mapping -->
			</div>
			
			<div class="form-group" id="rePasswordField">
				<label class="control-label" for="retype-password">Retype Password:</label>
				<input id="retype-password" class="form-control" type = "password" name = "rePassword" required="true"/>
			</div>
			<h4 style="color:red">${rePassword_error_msg}</h4>
			
			<div class="form-group" id="hasDriverLicense">
				<label class="control-label" for="driverOption">Do you have a driving license?</label>
				<div class="row">
					<label class="control-label">Yes</label>
					<input type="radio" name="driverLicense" value="Yes"/>
				</div>
				<div class="row">
					<label class="control-label">No</label>
					<input type="radio" name="driverLicense" value="No" checked="checked" />
				</div>
			</div>
			
			<input type="reset" value="reset" class="btn btn-warning"/>
			<input type="submit" name="registerSubmit" value="submit" class="btn btn-primary"/>
		</form>	
	</div>
	<%@ include file="Footer.jsp" %>
	
</body>
</html>