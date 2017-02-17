<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Your Mark</title>
</head>
<body>

<h2 class="moreSpaceBelow">Make it count!</h2>
    <p class="storyTitle"><c:out value="${message.title}"></c:out></p>
    <div class="storyContent"><pre><c:out value="${message.content}"></c:out></pre></div>
    <sf:form method="POST" action="${pageContext.request.contextPath}/addContribution" commandName="contribution">
		<sf:input class="control firstLine" type="text" name="addition" value="" id="addition"
							path="addition" />
	    <p class="remaining" id="remaining"><c:out value="${message.lineLength}"></c:out></p>
		<sf:input type="hidden" class="control" name="title" value = "${message.title}"
							path="title" />
		<p class="errorMessage"><c:out value="${message.error}"></c:out></p>
		<p class="link"><c:if test="${error.message == 'You must be logged in to contribute to a story.'}"><a href="<c:url value="/login"/>">log in</a></c:if></p>
		<input class="button submitButton" type="submit" value="Add it!" />
	</sf:form>

<div class="moreSpaceAbove spaceBelow indented">
	<p class="link"><a href="<c:url value="/"/>">Go Back Home</a></p>
</div>

<script type="text/javascript">

	$('#remaining').html(("${message.lineLength}" - $('#addition').val().length)+" characters remaining.");
	
	$(document).on("keyup", "#addition", function() {
		if($('#addition').val().length <= "${message.lineLength}") {
			$('#remaining').html(("${message.lineLength}" - $('#addition').val().length)+" characters remaining.");			
		}
		else {
			$('#remaining').html("Time to edit, you've exceeded the maximum character limit.");
		}

	});
</script>
</body>
</html>