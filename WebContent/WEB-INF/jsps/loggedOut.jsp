<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enjoy Anonymity</title>
</head>
<body>

<h2 class="spaceBelow">You are not signed in.</h2>

<div class="indented moreSpaceAbove spaceBelow">
	<p class="link"><a href="<c:url value="/"/>">Return Home</a></p>
</div>
</body>
</html>