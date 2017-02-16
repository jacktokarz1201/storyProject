<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reading Zone</title>
</head>
<body>

	<h2>Curl up by the fire</h2>

<sql:query var="rs" dataSource="jdbc/stories">
	select title, content from stories where completed = '1'
</sql:query>

<c:forEach var="story" items="${rs.rows}">
	<div class="storyTitle">
		<u><c:out value="${story.title}"></c:out></u>
	</div>
	<div class="storyContent">
		<pre><c:out value="${story.content}"></c:out></pre>
	</div>
	<br />	
    <br />
</c:forEach>

<a href="<c:url value="/newStory"/>">Start a new story!</a>
<a href="<c:url value="/"/>">Go Home</a>

</body>
</html>