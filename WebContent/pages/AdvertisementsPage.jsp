<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	body {
		text-align: center;
		background-color: #FFFFCC;
	}
</style>
</head>
<body>
	<%@ include file="Header.jsp" %>
	<h1>Search For Advertisements</h1>
	<form>
		From:
		<select>
			<!-- must be sorted by alphabetically -->
			<!-- can't visualize cyrillics -->
			<!-- use spring for these lists -->
			<option>Sofia</option>
			<option>Lovech</option>
			<option>Varna</option>
			<option>Blagoevgrad</option>
			<option>Burgas</option>
		</select>
		
		To:
		<select>
			<!-- must be sorted by alphabetically -->
			<option>Sofia</option>
			<option>Lovech</option>
			<option>Varna</option>
			<option>Blagoevgrad</option>
			<option>Burgas</option>
		</select>
	
  		Date:
  		<input type="date" name="bday">
  		
  		<input type="submit" value="Search!">
	</form>
	
	<%@ include file="Footer.jsp" %>
	<!-- 
	<table border="1">
		<tr>
			<th>Drive off hour</th>
			<td>blabla</td>
			<td>hihihi</td>
			<td>hehhe</td>
		</tr>
		<tr>
			<td>hoooohoo</td>
			<td>merry</td>
			<td>christmas</td>
		</tr>
	</table>
	 -->
	
</body>
</html>