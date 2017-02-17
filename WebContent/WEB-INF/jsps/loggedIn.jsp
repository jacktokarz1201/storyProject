<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>We're glad you could make it</title>
</head>
<body>
<h2 class="spaceBelow">Welcome back <c:out value="${user}" ></c:out></h2>

<div class="indented moreSpaceAbove spaceBelow">
	<p class="link"><a href="<c:url value="/newStory"/>">Start a new story!</a></p>
	<p class="link"><a href="<c:url value="/storiesInProgress"/>">Add to an existing story</a></p>
	<p class="link"><a href="<c:url value="/"/>">Go Home</a></p>
</div>
</body>
</html>