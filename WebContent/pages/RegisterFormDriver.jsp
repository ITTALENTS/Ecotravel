<%@page import="utils.MusicTypesContainer"%>
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
	<div id="welcome" class="container">
		<form id="registerFormDriver" method="Post" action="RegisterFormDriver">
			<div class="form-group" id="licenseSince">
				<label for="licensePeriod">License Year</label>
				<input id="licensePeriod" type="number" name="licensePeriodYear" min="1950" max="2014"  class="form-control">
			</div>
			
			<div class="form-group" id="SmokingInTheCar">
				<div class="row">
				<label class="control-label">Is smoking in the car allowed:</label>
				</div>
				<div class="row">
				<label for="yes">Yes</label>
				<input id="yes" type="radio" name="isSmoking" value="Yes">
				<label for="no">No</label>
				<input id="no" type="radio" name="isSmoking" value="No" checked="checked">
				</div>
			</div>
			
			<div class="form-group" id="musicInCar">
				<label for="musicInTheCar">What music is allowed in the car:</label>
				<select id="musicInTheCar" name="musicInTheCar" class="form-control">
					<% MusicTypesContainer.printMusicTypes(out);	%>
				</select>
			</div>
			
			<input type="submit" name="registerDriverSubmit" value="submit" class="btn btn-primary" />
		
		</form>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>