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

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js" ></script> 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
</head>
<body>
	<sf:form method="POST" action="${pageContext.request.contextPath}/createAuthor"
	commandName="author">
		<table>
			<tr>
				<td class="formTitle">Username:</td>
				<td><sf:input class="control" type="text" name="username"
						path="username" /> <br />
					<div class="errorMessage">
						<sf:errors path="username"></sf:errors>
					</div></td>
				<td class="formTitle">Password:</td>
				<td><sf:input class="control" type="password" name="password"
						 path="password" /> <br />
					<div class="errorMessage">
						<sf:errors path="password"></sf:errors>
					</div></td>
				<td class="formTitle">Confirm Password:</td>
				<td><sf:input class="control" type="password" name="confirm"
						 path="confirm" /> <br /></td>
				<td><input class="button" type="submit" value="Register" /></td>
		</table>
	</sf:form>
<br />
<c:out value="${error}"></c:out>

</body>
</html>