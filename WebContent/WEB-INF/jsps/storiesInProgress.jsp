<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stories In Progress</title>
</head>
<body>

<sql:query var="rs" dataSource="jdbc/stories">
	select title, content, lineLength, storyLength, authors from stories where completed = "false"
</sql:query>

	<h2>Stories you can add to:</h2>

	<c:forEach var="row" items="${rs.rows}">
<!-- This is a SUPER janky way to test if the user has already added to a story, then displays it if not. -->
<!-- First, separates the authors of the story -->
	<c:forTokens items="${row.authors}" delims="," var="author">
		<!-- Compares the username (passed by the model) to each author's name, if they're equal, match is above 1, this was a dumb, but certain, way to do a boolean -->
		<c:choose>
		<c:when test="${author eq user}">
			<c:set var="match" scope="session" value="${2}"/>
		</c:when>
		<c:otherwise>
			<c:set var="match" scope="session" value="${0}"/>
		</c:otherwise>
		</c:choose>
	</c:forTokens>
	<!-- If they didn't match, displays the ability to edit the story. -->
	<c:if test="${match < 1}">
			<sf:form method="POST"
			action="${pageContext.request.contextPath}/storySelected"
			commandName="contribution">
				<sf:input type="hidden" class="control" name="title"
					value="${row.title}" path="title" />
				<u><input class="button" type="submit" value="${row.title}" /></u>
				<pre><c:out value="${row.content}"></c:out></pre>
				<br />
				<i><p>Maximum of <c:out value="${row.lineLength}"></c:out> characters.</p>
				<p><c:out value="${row.storyLength}"></c:out> lines left to add.</p></i>
				<br /> <br />
			</sf:form>
		</c:if>
	</c:forEach>
	<i><c:out value="${error}"></c:out></i>
	<br />
	
	
	<h2>Stories you have already added to:</h2>

	<c:forEach var="row" items="${rs.rows}">
<!-- Inverse of above, displays the stories that do match, that they can't add to anymore -->
	<c:forTokens items="${row.authors}" delims="," var="author">
		<c:choose>
		<c:when test="${author eq user}">
			<c:set var="match" scope="session" value="${2}"/>
		</c:when>
		<c:otherwise>
			<c:set var="match" scope="session" value="${0}"/>
		</c:otherwise>
		</c:choose>
	</c:forTokens>
	<c:if test="${match > 1}">
				<u><c:out value="${row.title}"></c:out></u>
				<br />
				<pre><c:out value="${row.content}"></c:out></pre> <br />
				<i><p><c:out value="${row.storyLength}"></c:out> lines left to add.</p></i>
				<br /> <br />
	</c:if>
	</c:forEach>
	

	<a href="<c:url value="/"/>">Go Back Home</a>
</body>

<script type="text/javascript">
	
	function compareName(row, user) {
		console.log(user);
		var seperatedAuthors = row.authors.split(",");
		for(var i=0; i<seperatedAuthors.length; i++) {
			if(user.equals(seperatedAuthors[i])) {
				return false;
			}
		}
		return true;
	}

</script>


</html>