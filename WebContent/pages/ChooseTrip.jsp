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
	<%! static void printInCell(JspWriter out, String a) throws IOException{
			out.print("<td>");
				out.print(a);
			out.print("</td>");
		}
	
		static void printAnAdvertisment(JspWriter out, Addvertisment a) throws IOException{
			out.print("<tr>");
				printInCell(out, a.getDriver().getProfile().getEmail());
				printInCell(out, a.getTravelFrom());
				printInCell(out, a.getTravelTo());
				printInCell(out, a.getDate());
				printInCell(out, a.getTimeOfTravel());
				printInCell(out, String.valueOf(a.getFreePlaces()));
				printInCell(out, "<a href=\"driverProfile\" value=\"driverProfile\" />");
				printInCell(out, "<form method=\"GET\" action=\"SubscribeForTrip\"><input type=\"submit\" value=\"&#8594\"></form>");
			out.print("</tr>");
		}
	%>
		<form method="GET">
			<table border="1px solid red">
				<% 
					for(Addvertisment a : (ArrayList<Addvertisment>)session.getAttribute("all_valid_advertisements")) 
					{
						printAnAdvertisment(out, a);
					} 
				%>
			
			</table>
		</form>
	<%@ include file="Footer.jsp" %>
</body>
</html>