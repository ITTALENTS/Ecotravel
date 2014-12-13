<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	
	<h1>My Profile:</h1>
	<hr>
	<p>Name: ${name} <p>
	<p>Username: ${username} </p>
	<p>Year of birth: ${birthYear}</p>
	<p>E-mail: ${email} </p>
	<p>Phone: ${phone} </p>
	<p>Driving License: No</p>
	<p>Rating: ${rating }</p>
		
	<form method="GET">
		<input type="submit" value="Edit Profile" />
	</form>
	
	<%@ include file="Footer.jsp" %>
</body>
</html>