<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Where the Heart Is</title>
</head>
<body>

<c:choose>
	<c:when test="${now == null}">
		<a href="<c:url value="/login"/>">log in</a>
	</c:when>
	<c:otherwise>
		<p>Welcome back <c:out value= "${now}"></c:out>
		<a href="<c:url value="/loggedOut"/>">log out</a>
	</c:otherwise>
</c:choose>
<br />

<sql:query var="rs" dataSource="jdbc/stories">
select username, password from authors
</sql:query>

  <h2>Home</h2>

<c:forEach var="row" items="${rs.rows}">
    Username: <c:out value="${row.username}"></c:out> <br />
    Password: ${row.password}<br/>
</c:forEach>


<a href="<c:url value="/completedStories"/>">Read the completed stories here</a>
<br />
<a href="<c:url value="/newStory"/>">Start a new story!</a>
<br />
<a href="<c:url value="/storiesInProgress"/>">Add to an existing story!</a>
<br />

</body>
</html>