<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<nav>
		<div>
			<ul>
				<li><a href="#">About Us</a></li>
				<li><a href="#">History</a></li>
				<li><a href="ProfilePage">My Profile</a></li>	
			</ul>
		</div>
		
	</nav>
	
	<% if(!this.getClass().getSimpleName().equalsIgnoreCase("Welcome_jsp") &&
			!this.getClass().getSimpleName().equalsIgnoreCase("RegisterForm_jsp")) { %>
		<form method="POST" action="Logout">
			<input type="submit" value="Logout" />
		</form>
	<% } %>
	
	<!-- <h3><% out.print(this.getClass().getSimpleName()); %></h3>  -->
	
	<hr />
	
</body>
</html>