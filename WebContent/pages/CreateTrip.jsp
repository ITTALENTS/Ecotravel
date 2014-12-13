<%@page import="jdbc.model.TownsContainer"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Trip</title>
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
  		
  		Time:
  		<input type="time" name="time" >
  		
  		Number of Free places
  		<input type="number" name="freePlaces" min="1" max="6">
  		
  		<input type="submit" value="Create">
	</form>
</body>
</html>