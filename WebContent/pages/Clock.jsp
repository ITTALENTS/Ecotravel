<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Road trip</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="refresh" content="1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-theme.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/site.css">
</head>
<body>
	<% Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 2);
		c.set(Calendar.HOUR_OF_DAY, 1);
		c.set(Calendar.MILLISECOND, 0);
		out.print(c);
	%>
</body>
</html>