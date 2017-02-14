<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js" ></script>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
</head>
<body>

	<sf:form method="POST" action="${pageContext.request.contextPath}/createUser"
	commandName="user">
		<table>
			<tr>
				<td class="formTitle">Username:</td>
				<td><sf:input class="control" type="text" name="username"
						path="username" /> <br />
					<div class="errorMessage">
						<sf:errors path="username"></sf:errors>
					</div></td>
				<td class="formTitle">Password:</td>
				<td><sf:input class="control" type="password" name="password" id="p1"
						 path="password" /> <br />
					<div class="errorMessage">
						<sf:errors path="password"></sf:errors>
					</div></td>
				<td class="formTitle">Confirm Password: </td>
				<td><input type="password" id = "p2"/><br />
				
				<td><input class="button" type="submit" value="Register" onclick= "checkPasswords()" /></td>
		</table>
	</sf:form>

</body>
</html>