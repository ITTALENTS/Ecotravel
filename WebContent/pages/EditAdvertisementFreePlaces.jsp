<%@page import="org.apache.catalina.Session"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
	<form method="POST" action="EditFreePlaces" class="form-horizontal">
		<%! static void printInCell(JspWriter out, String a) throws IOException{
			out.print("<div class=\"col-lg-1\">");
				out.print(a);
			out.print("</div>");
		}
	
		static void printAnAdvertisment(JspWriter out, Addvertisment a) throws IOException{
			out.print("<div class=\"row trip-row\">");
				String driverUsername = a.getDriver().getProfile().getUsername();
				printInCell(out, a.getTravelFrom());
				printInCell(out, a.getTravelTo());
				printInCell(out, a.getDate().substring(2));
				printInCell(out, a.getTimeOfTravel());
				out.print("<div class=\"col-lg-2\"><input type=\"number\" min=\"1\" max=\"6\" name=\"freePlaces\" value=\"Free places\">"
						 + "<input type=\"submit\" value=\"Confirm\" class=\"btn btn-info\"/></div>");
				out.print("</div>");
		}
		%>
		<%
		out.print(  "<div class=\"row\">"
		 		+ "<div class=\"col-lg-1\">From</div>"
 		        + "<div class=\"col-lg-1\">To</div>" 
 		  	    + "<div class=\"col-lg-1\">Date</div>"
  	    		+ "<div class=\"col-lg-1\">Time</div>"
   				+ "<div class=\"col-lg-1\">Free Places</div>"
  	    		+ "</div>");
		for(Addvertisment a : (ArrayList<Addvertisment>)session.getAttribute("active_ads")) {
			printAnAdvertisment(out, a);
		}
	%>
		</form>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>