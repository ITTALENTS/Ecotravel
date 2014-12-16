<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Road trip</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	<div id="welcome" class="container">
		<h1>My Profile:</h1>
		<div class="text-success">
			<p>Name: ${name} <p>
			<p>Username: ${username} </p>
			<p>Year of birth: ${birthYear}</p>
			<p>E-mail: ${email} </p>
			<p>Phone: ${phone} </p>
			<p>Driving License: No</p>
			<p>Rating: ${rating }</p>
		</div>
		<form method="GET" action="EditProfile">
			<input type="submit" value="Edit Profile" class="btn btn-info"/>
		</form>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>