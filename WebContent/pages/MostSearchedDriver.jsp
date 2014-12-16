<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp" %>
	<div id="welcome" class="container">
		<% 
			out.print(  "<div class=\"row\">"
					 		  		+ "<div class=\"col-lg-2\">USERNAME</div>"
					 		  		+ "<div class=\"col-lg-3\">Number Of Resieved Mails</div>"
					 			+ "</div>");
				List<Driver> listOfDrivers = (List<Driver>)session.getAttribute("most_wanted_drivers");	
				for(Driver currentDriver : listOfDrivers) 
				{   out.print("<div class=\"row\">");
					out.print("<div class=\"col-lg-2\">"+ currentDriver.getProfile().getUsername()+"</div>");
					out.print("<div class=\"col-lg-3\">"+ currentDriver.getRating()+"</div>");
					out.print("</div>");
				}
				%>		
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>