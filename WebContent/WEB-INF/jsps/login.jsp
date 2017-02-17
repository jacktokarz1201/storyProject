<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>How's Your Memory?</title>
</head>
<body>

<h2 class="moreSpaceBelow">Identify Yourself</h2>

<c:if test="${error != null}">
	<p class="errorMessage"><c:out value="${error}"></c:out></p>
</c:if>

<sf:form method="POST" name= "f" action="${pageContext.request.contextPath}/checkLogin" commandName="author">
		<table>
			<tr>
				<td class="formTitle">Username:</td>
				<td><sf:input class="control" type="text" name="username" path="username" />
				</td>
			</tr>
			<tr>
				<td class="formTitle">Password:</td>
				<td><sf:input class="control" type="password" name="password" path="password" />
				</td>
			</tr>
			<tr>
				<td><input class="button submitButton" name= "submit" type="submit" value="Log In" /></td>
		</table>
	</sf:form>

<div class="spaceAbove">
	<p class="link"><a href="<c:url value="/register"/>">Register as a new author</a></p>
</div>
</body>
</html>