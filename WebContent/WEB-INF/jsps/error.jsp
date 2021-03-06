<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERROR</title>
</head>
<body>

<h1>There's been an error!</h1>

<div class="indented moreSpaceAbove">
<c:if test="${error != null}">
	<p class="errorMessage"><c:out value="${error}"></c:out></p>
	
	<p class="link"><a href="<c:url value="/"/>">Go Home</a></p>
	<p class="link"><a href="<c:url value="/login"/>">log in</a></p>
</c:if>
</div>
</body>
</html>