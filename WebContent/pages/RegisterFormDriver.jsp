<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>THIS PAGE IS UNDER CONSTRUCTION</h1>

	<form id="registerFormDriver" method="Post" action="#">
	<p id="licenseSince">
		<label for="licensePeriod">License</label>
		Day: <input type="number" name="licensePeriodDay" min="1" max="31">
		Month: <input type="number" name="licensePeriodMonth" min="1" max="12">
		Year: <input type="number" name="licensePeriodYear" min="1" max="2014">
	</p>
	
	<p id="SmokingInTheCar">
		<p>Is smoking in the car allowed:</p>
		<label for="yes"></label>
		<input type="radio" name="smoking" value="Yes"> Yes <br />
		<label for="no"></label>
		<input type="radio" name="smoking" value="Yes"> No <br />
	</p>
	
	<p id="musicInCar">
		<span>Is smoking in the car allowed:</span>
		<select>
			<option name="everything" checked>Everything</option>
			<option name="popFolk">Pop folk</option>
			<option name="pop">Pop</option>
			<option name="rock">Rock</option>
			<option name="folk">Folk</option>
		</select>
	</p>
	
	<p id="rePasswordField">
		<label for="password">Retype Password:</label>
	<input type="submit" name="registerDriverSubmit" value="submit" />
</form>
		<%@ include file="Footer.jsp" %>
</body>
</html>