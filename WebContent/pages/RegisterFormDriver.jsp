<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form id="registerFormDriver" method="Post" action="#">
	<p id="licenseSince">
		<label for="licensePeriod">:</label>
		Day: <input type = "text" name = "licensePeriodDay" required="required" />
		Month: <input type = "text" name = "licensePeriodMonth" required="true" />
		Year: <input type = "text" name = "licensePeriodYear" required="true" />
	</p>
	
	<p id="numberOfTrips">
		Trip count:
		<!-- Tuk s jsp pokazvame broq na putuvaniqta -->
	</p>
	
	<p id="emailField">
		<label for="email">Email:</label>
		<input type = "email" name = "email" required="true"/>
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
		<input type="radio" name="driver license" value="Yes"/>Yes
		<input type="radio" name="driver license" value="No" checked="checked" />No
	</p>
	
	<input type="submit" name="registerSubmit" value="submit" />
</form>
</body>
</html>