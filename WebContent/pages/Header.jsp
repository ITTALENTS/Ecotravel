<%@page import="jdbc.model.Passenger"%>
<%@page import="jdbc.model.Driver"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-theme.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/site.css">
	<title>Road trip</title>
</head>
<body>
		<div class="navbar navbar-default">
	  <div class="navbar-header">
	    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	    </button>
	    <a class="navbar-brand" href="#">RoadTrip</a>
	  </div>
	  <div class="navbar-collapse collapse navbar-responsive-collapse">
	    <ul class="nav navbar-nav">
	    
	    	<li><a href="Home">Home</a></li>
	    	
	    	<% 
	    	
// 	    		if(session.getAttribute("loggedInUser") instanceof  Driver){
// 	    			out.print("<li><a href=\"ChooseForm\">Home</a></li>");
// 	    		}
// 	    		else if(session.getAttribute("loggedInUser") instanceof  Passenger){
// 	    			out.print("<li><a href=\"AdvertisementsPage\">Home</a></li>");
// 	    		}
// 	    		else{
// 	    	    	out.print("<li><a href=\"Welcome\">Home</a></li>");
// 	    		}
	    		
	    	%>
	      	<li><a href="AboutUs">About Us</a></li>
			<li><a href="History">History</a></li>			
	    </ul>
	    <ul class="nav navbar-nav pull-right">
	    <% if(session.getAttribute("loggedInUser") != null) { %>
				<li><a href="Profile" class="text-info">My Profile</a></li>
				<li><form method="POST" action="Logout">
					<input type="submit" value="Logout" class="btn btn-danger" />
				</form>
				</li>
		<% } %>
	    </ul>
		</div>
	  </div>
	<!-- <h3><% out.print(this.getClass().getSimpleName()); %></h3>  -->	
</body>
</html>