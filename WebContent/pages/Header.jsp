<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Header.css">
</head>

	<nav>
		<div id="header">
				<ul>
					<li><%= new java.util.Date() %></li>
					<li><a href="AboutUs">About Us</a></li>
					<li><a href="History">History</a></li>
				</ul>
			
		
		<% if(!this.getClass().getSimpleName().equalsIgnoreCase("Welcome_jsp") &&
				!this.getClass().getSimpleName().equalsIgnoreCase("RegisterForm_jsp") &&
				!this.getClass().getSimpleName().equalsIgnoreCase("RegisterFormDriver_jsp") &&
				!this.getClass().getSimpleName().equalsIgnoreCase("ForgottenPassword_jsp")) { %>
					<li><a href="Profile">My Profile</a></li>
				<form method="POST" action="Logout">
					<input type="submit" value="Logout" />
				</form>
		<% } %>
		
		<!-- <h3><% out.print(this.getClass().getSimpleName()); %></h3>  -->
			<hr />
		</div>
	</nav>
</body>
</html>