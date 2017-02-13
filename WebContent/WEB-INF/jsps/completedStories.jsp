<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reading Zone</title>
</head>
<body>

	<h2>Curl up by the fire</h2>

<sql:query var="rs" dataSource="jdbc/stories">
select title from stories
</sql:query>

<c:forEach var="story" items="${rs.rows}">
	<c:out value="${story.title}"></c:out><br/>
    <br />
</c:forEach>

	<a href="<c:url value="/"/>">Go Home</a>



</body>
</html>