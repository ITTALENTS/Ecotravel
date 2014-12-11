<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	<form id="registerForm" method="Post" action="register">
		<p id="nameField">
			<label for="name">Name:</label>
			<input type = "text" name = "name" required="true"/>
		</p>
		
		<p id="age">
			<label for="birthYear">Birth Year:</label>
			<input type="number" name="year" min="1990" max="2000">
		</p>
		
		<p id="phoneField">
			<label for="telephone">Phone:</label>
			<input type = "tel" name = "telephone" required="true"/>
		</p>
		
		<p id="emailField">
			<label for="email">Email:</label>
			<input type = "email" name = "email" required="true"/>
		</p>
		
		<p id="sernameFiled">
			<label for="username">Username:</label>
			<input type = "text" name = "username" required="true"/>
		</p>
		
		<p id="passwordField">
			<label for="password">Password:</label>
			<input type = "password" name = "password" required="true"/>
		</p>
		
		<p id="rePasswordField">
			<label for="password">Retype Password:</label>
			<input type = "password" name = "rePassword" required="true"/>
		</p>
		
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