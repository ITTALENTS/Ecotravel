<%@page import="java.io.IOException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.model.Addvertisment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Found Advertisements</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	<div id="welcome" class="container">
	<%! static void printInCell(JspWriter out, String a) throws IOException{
			out.print("<div class=\"col-lg-1\">");
				out.print(a);
			out.print("</div>");
		}
	
		static void printAnAdvertisment(JspWriter out, Addvertisment a) throws IOException{
			out.print("<div class=\"row\">");
				String driverUsername = a.getDriver().getProfile().getUsername();
				printInCell(out,"<ul class=\"pager\"><li class=\"next\"><input class=\"btn btn-primary\" type=\"radio\" name=\"selectedAdv\" value=" + driverUsername + "></li></ul>");
				printInCell(out, driverUsername);
				printInCell(out, a.getTravelFrom());
				printInCell(out, a.getTravelTo());
				printInCell(out, a.getDate());
				printInCell(out, a.getTimeOfTravel());
				printInCell(out, String.valueOf(a.getFreePlaces()));
				printInCell(out, "<a href=\"\" value=\"driverProfile\" class=\"btn btn-primary\">Driver Profile</a>"); 
			out.print("</div>");
		}
	%>
		<form method="GET" name="selectedAdvertisment">
				<% 
					out.print(  "<div class=\"row\">"
									+ "<div class=\"col-lg-1\">Select Trip</div>"
							 		+ "<div class=\"col-lg-1\">Driver</div>"
					 		  		+ "<div class=\"col-lg-1\">From</div>"
					 		        + "<div class=\"col-lg-1\">To</div>" 
					 		  	    + "<div class=\"col-lg-1\">Date</div>"
					 		  		+ "<div class=\"col-lg-1\">Time</div>"
					 		  		+ "<div class=\"col-lg-1\">Free places</div>"
					 			+ "</div>");
					
				for(Addvertisment a : (ArrayList<Addvertisment>)session.getAttribute("matching_advertisements")) 
					{
						printAnAdvertisment(out, a);
					}
					out.print("<ul class=\"pager\"><li class=\"next\"><input class=\"btn btn-primary\" type=\"submit\" value=\"Connect this driver\"></li></ul>");
				%>
		</form>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>