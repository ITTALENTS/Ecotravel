<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- @ taglib prefix="sf" uri="http://www.springframework.org/tags/form" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>

	<%@ include file="Header.jsp" %>
	
	<form id="registerForm" method="POST" action="RegisterForm">
		<p id="nameField">
			<label for="name">Name:</label>
			<input type = "text" name = "name" required="true"/>
		</p>
		
		<p id="age">
			<label for="birthYear">Birth Year:</label>
			<input type="number" name="birthYear" min="1990" max="2000">
		</p>
		
		<p id="phoneField">
			<label for="telephone">Phone:</label>
			<input type = "tel" name = "telephone" required="true"/>
		</p>
		
		<p id="emailField">
			<label for="email">Email:</label>
			<input type = "email" name = "email" required="true"/>
			<!-- name should be "profile.email" to to the mapping -->
		</p>
		<h4 style="color:red">${email_taken_msg}</h4>
		
		<p id="usernameFiled">
			<label for="username">Username:</label>
			<input type = "text" name = "username" required="true"/>
			<!-- name should be "profile.username" to to the mapping -->
		</p>
		<h4 style="color:red">${username_taken_msg}</h4>
		
		<p id="passwordField">
			<label for="password">Password:</label>
			<input type = "password" name = "password" required="true"/>
			<!-- name should be "profile.password" to to the mapping -->
		</p>
		
		<p id="rePasswordField">
			<label for="password">Retype Password:</label>
			<input type = "password" name = "rePassword" required="true"/>
		</p>
		<h4 style="color:red">${rePassword_error_msg}</h4>
		
		<p id="hasDriverLicense">
			<label for="driverOption">Do you have a driving license?</label>
			<input type="radio" name="driverLicense" value="Yes"/>Yes
			<input type="radio" name="driverLicense" value="No" checked="checked" />No
		</p>
		
		<input type="submit" name="registerSubmit" value="submit" />
	</form>	
	
	<%@ include file="Footer.jsp" %>
	
</body>
</html>