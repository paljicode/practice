<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h1>Welcome to the Home Page</h1>
	<p>This is the home page of the application.</p>
	<p>
		<a href="${pageContext.request.contextPath}/mvc/employee/register">Register</a> or <a href="${pageContext.request.contextPath}/mvc/employee/login">Login</a>
	</p>

</body>
</html>