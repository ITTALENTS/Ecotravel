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
				printInCell(out, a.getDriver().getProfile().getUsername());
				printInCell(out, a.getTravelFrom());
				printInCell(out, a.getTravelTo());
				printInCell(out, a.getDate());
				//printInCell(out, a.getTime());
				printInCell(out, String.valueOf(a.getFreePlaces()));
				printInCell(out, "<a href=\"driverProfile\" value=\"driverProfile\" class=\"btn btn-primary\">Driver Profile</a>");
				printInCell(out, "<ul class=\"pager\"><li class=\"next\"><input btn btn-warning type=\"submit\" value=\"U+21f0\"></li></ul>");
			out.print("</div>");
		}
	%>
		<form method="GET" name="selectedAdvertisment">
				<% 
					for(Addvertisment a : (ArrayList<Addvertisment>)session.getAttribute("all_valid_advertisements")) 
					{
						printAnAdvertisment(out, a);
					} 
				%>
		</form>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>