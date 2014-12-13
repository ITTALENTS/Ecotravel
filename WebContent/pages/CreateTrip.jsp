<%@page import="jdbc.model.TownsContainer"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form>
		From:
		<select>
			<!-- use spring for these lists -->
			<% TownsContainer.printTownsInSelectMenu(out);	%>
		</select>
		
		To:
		<select>
			<% TownsContainer.printTownsInSelectMenu(out);	%>
		</select>
	
  		Date:
  		<input type="date" name="bday">
  		
  		<input type="submit" value="Search!">
	</form>
</body>
</html>