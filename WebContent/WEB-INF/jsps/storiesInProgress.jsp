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

	<h2 class="moreSpaceBelow">Stories you can add to:</h2>

	<p class="spaceBelow">----------------------------------------------------------------------------------------------------</p>
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
				<p><input class="button titleButton" type="submit" value="${row.title}" />
				<span class="authorsListing"> by: <c:out value="${row.authors}"></</c:out></span></p>
				<pre><c:out value="${row.content}"></c:out></pre>
				<p class="storyInformation indented"><c:out value="${row.lineLength}"></c:out> characters per line.
				<br />
				<c:out value="${row.storyLength}"></c:out> lines left to add.</p>
			</sf:form>
			<p class="spaceBelow">----------------------------------------------------------------------------------------------------</p>
		</c:if>
	</c:forEach>
	<i><c:out value="${error}"></c:out></i>
	<br />
	
	
	<h2 class="moreSpaceBelow">Stories you have already added to:</h2>
<p class="spaceBelow">----------------------------------------------------------------------------------------------------</p>
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
				<p><span class="storyTitle"><c:out value="${row.title}"></c:out></span>
				<span class="authorsListing"> by: <c:out value="${row.authors}"></</c:out></span></p>
				<div class="storyContent"><pre><c:out value="${row.content}"></c:out></pre></div>
				<p class="storyInformation indented"><c:out value="${row.storyLength}"></c:out> lines left to be added.</p>
				<p class="spaceBelow">----------------------------------------------------------------------------------------------------</p>
	</c:if>
	</c:forEach>
	
<div class="moreSpaceAbove spaceBelow indented">
	<a href="<c:url value="/"/>">Go Back Home</a>
</div>

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