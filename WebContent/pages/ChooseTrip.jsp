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
	<%! static void printInCell(String a){
			out.print("<td>");
				out.print(a);
			out.print("</td>");
		
		}
	%>
	}
		<form method="GET">
			<table>
				<!-- ${trips} -->
				
				<% 
				for(Addvertisment a : (ArrayList<Addvertisment>)session.getAttribute("all_valid_advertisements")) 
				{
					out.print("<tr>");
						out.print("<td>");
							out.print(a.getTravelFrom());
						out.print("</td>");
						out.print("<td>");
							out.print(a.getTravelFrom());
						out.print("</td>");
						out.print("<td>");
							out.print(a.getTravelFrom());
						out.print("</td>");
						out.print("<td>");
							out.print(a.getTravelFrom());
						out.print("<td>");
					out.print("</tr>");
				} 
				%>
			
			</table>
		</form>
	<%@ include file="Footer.jsp" %>
</body>
</html>