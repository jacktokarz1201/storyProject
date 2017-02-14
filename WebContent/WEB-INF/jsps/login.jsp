<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identify Yourself</title>
</head>
<body onload='document.f.j_username.focus();'>

<h2>Welcome back!</h2>

<c:if test="${param.error != null}">
	<p class="loginError">There was a problem logging in, make sure your username and password are entered correctly.</p>
	Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
</c:if>

<form method="post" name= "f" action="${pageContext.request.contextPath}/j_spring_security_check">
		<table>
			<tr>
				<td class="formTitle">Username:</td>
				<td><input class="control" type="text" name="j_username" /> <br />
						</td>
				<td class="formTitle">Password:</td>
				<td><input class="control" type="password" name="j_password" /> <br />
						</td>

				<td><input class="button" name= "submit" type="submit" value="Log In" /></td>
		</table>
	</form>


<a href="<c:url value="/register"/>">Register as a new user</a>

</body>
</html>