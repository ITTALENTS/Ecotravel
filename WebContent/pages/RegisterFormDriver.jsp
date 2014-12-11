<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form id="registerFormDriver" method="Post" action="#">
	<p id="licenseSince">
		<label for="licensePeriod">License</label>
		Day: <input type="text" name = "licensePeriodDay" required="required" />
		Month: <input type="text" name = "licensePeriodMonth" required="true" />
		Year: <input type="number" name = "licensePeriodYear" required="true" min="1930" max="2014"/>
	</p>
	
	<p id="doesSmoke">
		Do you smoke in car?
		<input type="radio" name="smoke" value="Yes"/>Yes
		<input type="radio" name="smoke" value="No" checked="checked" />No
	</p>
	
	<input type="submit" name="registerSubmit" value="Finish" />
</form>
</body>
</html>