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

<sql:query var="rs" dataSource="jdbc/stories">
select username, password from authors
</sql:query>


<div class="signin"><c:choose>
	<c:when test="${now == null}">
		<a href="<c:url value="/login"/>">log in</a>
	</c:when>
	<c:otherwise>
		<p>Welcome back: <i><c:out value= "${now}"></c:out></i>
		<br />
		<a href="<c:url value="/loggedOut"/>">log out</a></p>
	</c:otherwise>
</c:choose>
</div>
<h1>Collaborative Story Community Space</h1>

<h3>Members</h3>
<div class="indented">
<c:forEach var="row" items="${rs.rows}">
    <p class="user"><c:out value="${row.username}"></c:out></p>
</c:forEach>
</div>

<h3>Links</h3>
<div class="indented spaceBelow">
<c:choose>
	<c:when test="${now == null}">
		<p class="link"><a href="<c:url value="/completedStories"/>">Read the completed stories here</a></p>
		<p class="link">To work on a story, you must <a href="<c:url value="/login"/>">log in.</a></p>
		<p class="link">Or, <a href="<c:url value="/register"/>">register</a> as a new user.</p>
	</c:when>
	<c:otherwise>
		<p class="link"><a href="<c:url value="/completedStories"/>">Read the completed stories here</a></p>
		<p class="link"><a href="<c:url value="/newStory"/>">Start a new story!</a></p>
		<p class="link"><a href="<c:url value="/storiesInProgress"/>">Add to an existing story!</a></p>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>