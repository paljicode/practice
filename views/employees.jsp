<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employees</title>
</head>
<body>
   <h1>List of Registered Employees</h1>
    <table>
        <tr>
            <th>name</th>
            <!-- Add more columns if needed -->
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.name}</td>
                <!-- Add more columns if needed -->
            </tr>
        </c:forEach>
    </table>

</body>
</html>