<%@page import="org.apache.catalina.Session"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.List"%>
<%@page import="jdbc.model.Addvertisment"%>
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
	<form method="GET" action="EditProfile" class="form-horizontal">
		<%! static void printInCell(JspWriter out, String a) throws IOException{
			out.print("<div class=\"col-lg-1\">");
				out.print(a);
			out.print("</div>");
		}
		%>
		
		<%
		 	List<Addvertisment> listOfAllActiveAds = (List<Addvertisment>)(session.getAttribute("active_ads"));
			if(!listOfAllActiveAds.isEmpty()) { 
				out.print(  "<div class=\"row\">"
			 		+ "<div class=\"col-lg-1\">From</div>"
	 		        + "<div class=\"col-lg-1\">To</div>" 
	 		  	    + "<div class=\"col-lg-1\">Date</div>"
	 		  		+ "<div class=\"col-lg-1\">Time</div>"+ "</div>");
	 		  		
				out.print("<div class=\"col-lg-1\"><input type=\"number\" min=\"1\" max=\"6\" value=\"Free places\"> </div>");
			}
		 %>		
			<input type="submit" value="Confirm" class="btn btn-info"/>
		</form>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>