<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Your Mark</title>
</head>
<body>

<h2>Make it count!</h2>
    <u><c:out value="${message.title}"></c:out></u> <br />
    <pre><c:out value="${message.content}"></c:out></pre> <br />
    <p>Maximum of <c:out value="${message.lineLength}"></c:out> characters.</p>
    <sf:form method="POST" action="${pageContext.request.contextPath}/addContribution" commandName="contribution">
	<sf:input class="control" type="text" name="addition" value=""
						path="addition" /> <br />
	<sf:input type="hidden" class="control" name="title" value = "${message.title}"
						path="title" />
	<td><input class="button" type="submit" value="Add it!" /></td>
	</sf:form>

<i><c:out value="${message.error}"></c:out></i>
<br />

<a href="<c:url value="/"/>">Go Back Home</a>
<br />
<c:if test="${error.message == 'You must be logged in to contribute to a story.'}"><a href="<c:url value="/login"/>">log in</a></c:if>

</body>
</html>