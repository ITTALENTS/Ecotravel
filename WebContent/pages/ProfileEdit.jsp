<%@page import="org.apache.catalina.Session"%>
<%@page import="utils.MusicTypesContainer"%>
<%@page import="jdbc.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% Person currentUser = (Person)session.getAttribute("loggedIn");%>
	<%@ include file="Header.jsp" %>
	<div id="welcome" class="container">
		<form id=editForm" method="Post" action="SubmitNewProfile">
			<div class="form-group" id="nameField">
				<label class="control-label" for="name">Name:</label>
				<input class="form-control" id="name" type = "text" name = "name" value=\"<%=currentUser.getName()%>\" %> />
			</div>
			
			<div class="form-group" id="age">
				<label class="control-label" for="birthYear">Birth Year:</label>
				<input class="form-control" id="birthYear" type="number" name="birthYear" min="1990" max="2000" value=\"<%= currentUser.getBirthYear()%>\" />
			</div>
			
			<div class="form-group" id="phoneField">
				<label class="control-label" for="telephone">Phone:</label>
				<input class="form-control" id="telephone" type = "tel" name = "telephone" value=\"<%= currentUser.getTelephone()%>\"/>
			</div>
			
			<div class="form-group" id="emailField">
				Email:
				<div><%=currentUser.getProfile().getEmail()%></div>
			</div>
			<h4 style="color:red">${email_taken_msg}</h4>
			
			<div class="form-group" id="usernameFiled">
				Username:
				<div><%=currentUser.getProfile().getUsername()%></div>
			</div>
			<h4 style="color:red">${username_taken_msg}</h4>
			
			<div class="form-group" id="passwordField">
				<label class="control-label" for="password">Password:</label>
				<input id="password" class="form-control" type = "password" name = "password" required="true"/>
				<!-- name should be "profile.password" to to the mapping -->
			</div>
			
			<div class="form-group" id="rePasswordField">
				<label class="control-label" for="retype-password">Retype Password:</label>
				<input id="retype-password" class="form-control" type = "password" name = "rePassword" required="true"/>
			</div>
			<h4 style="color:red">${rePassword_error_msg}</h4>
			
				
			<%if(currentUser instanceof Driver){ %>			
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
						<% MusicTypesContainer.printMusicTypes(out);%>
					</select>
				</div>
			<%} %>
			<input type="submit" name="registerDriverSubmit" value="submit" class="btn btn-primary" />
		</form>
	</div>
	<%@ include file="Footer.jsp" %>
</body>
</html>