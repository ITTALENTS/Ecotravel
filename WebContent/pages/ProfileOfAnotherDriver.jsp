<%@page import="java.io.IOException"%>
<%@page import="java.util.List"%>
<%@page import="jdbc.model.Addvertisment"%>
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
		<%! static void printInCell(JspWriter out, String a) throws IOException{
			out.print("<div class=\"col-lg-1\">");
				out.print(a);
			out.print("</div>");
		}
	
		static void printAnAdvertisment(JspWriter out, Addvertisment a) throws IOException{
			out.print("<div class=\"row\">");
				printInCell(out, a.getTravelFrom());
				printInCell(out, a.getTravelTo());
				printInCell(out, a.getDate());
				printInCell(out, a.getTimeOfTravel());
				printInCell(out, String.valueOf(a.getFreePlaces()));
			out.print("</div>");
		}
	%>
	<div id="welcome" class="container">
		<h1>Driver's Profile:</h1>
		<hr>
		<div class="text-success">
			<p>Name: <strong>${name}</strong> <p>
			<p>Username: ${username} </p>
			<p>Year of birth: ${birthYear}</p>
			<p>E-mail: ${email} </p>
			<p>Phone: ${phone} </p>
			<p>Driving License: Yes</p>
			<p>License year: ${licenseYear}</p>
			<p>Is smoking in car allowed: ${isSmoking}</p>
			<p>Music: ${music} </p>
			<p>Number of travels: ${numberOfTravels}</p>
			<p>Rating: ${rating }</p>
		</div>
		<!-- HERE TO PRINT USER'S ADVERTISEMENT !!! -->
		
		<% List<Addvertisment> listOfAllActiveAds = (List<Addvertisment>)(session.getAttribute("active_ads"));
		if(!listOfAllActiveAds.isEmpty()) { 
			out.print(  "<div class=\"row\">"
			 		+ "<div class=\"col-lg-1\">From</div>"
	 		        + "<div class=\"col-lg-1\">To</div>" 
	 		  	    + "<div class=\"col-lg-1\">Date</div>"
	 		  		+ "<div class=\"col-lg-1\">Time</div>"
	 		  		+ "<div class=\"col-lg-1\">Free places</div>"
	 			+ "</div>");
			for(int i=0; i < listOfAllActiveAds.size(); i++)
				printAnAdvertisment(out, listOfAllActiveAds.get(i));
			} %>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>