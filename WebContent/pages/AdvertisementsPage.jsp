<%@page import="jdbc.model.TownsContainer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Advertisements</title>
<style>
	body {
		text-align: center;
		background-color: #FFFFCC;
	}
</style>
</head>
<body>
	<%@ include file="Header.jsp" %>
	<div id="welcome" class="container">
		<div class="jumbotron"><h1>Search For Advertisements</h1></div>
		<form method="GET" action="SearchAdvertisement">
			<div class="form-group">
				<label for="fromCity" class="col-md-2 control-label">From:</label> 
				<div class="col-md-10">
					<select id="fromCity" name="fromCity" class="form-control">
						<!-- use spring for these lists -->
						<% TownsContainer.printTownsInSelectMenu(out);	%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="toCity" class="col-md-2 control-label">To:</label> 
				<div class="col-md-10">
					<select id="toCity" name="toCity" class="form-control">
						<!-- use spring for these lists -->
						<% TownsContainer.printTownsInSelectMenu(out);	%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="date" class="col-md-2 control-label">Date:</label> 
				<div class="col-md-10">
					<input id="date" type="date" name="date" required="required" class="form-control">
				</div>
			</div>
			
	  		<input type="submit" value="Search!" class="btn btn-primary">
		</form>
		
		<h3>${searching_msg}</h3>
		<h3>${email_sent_msg}</h3>
	</div>
	
	<%@ include file="Footer.jsp" %>	
</body>
</html>