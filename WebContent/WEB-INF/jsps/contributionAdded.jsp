<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Awesome!</title>
</head>
<body>

<p>You have successfully added to <u><c:out value="${name}"></c:out></u>.
<br />

<a href="<c:url value="/"/>">Return Home</a>
<br />
<a href="<c:url value="/completedStories"/>">Read the completed stories</a>
<br />


</body>
</html>