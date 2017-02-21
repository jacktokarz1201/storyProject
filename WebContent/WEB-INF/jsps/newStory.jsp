<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.js" ></script>

<title>Sing, o Muse!</title>
</head>
<body>
<h2 class="starter">Begin Your Tale</h2>

	<sf:form method="POST" action="${pageContext.request.contextPath}/createStory"
	commandName="story">
		<table class="spaceBeow">
			<tr>
				<td class="formTitle">Characters per Line:</td>
				<td><sf:input class="control" type="text" name="lineLength" id="lineLength" path="lineLength" /> <br />
					<div class="errorMessage">
						<sf:errors path="lineLength"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="formTitle">Total Lines:</td>
				<td><sf:input class="control" type="text" name="storyLength" path="storyLength" /> <br />
					<div class="errorMessage">
						<sf:errors path="storyLength"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="formTitle">Title:</td>
				<td><sf:input class="control titleInput" type="text" name="title"
						path="title" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<div class="errorMessage">
						<sf:errors path="title"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="formTitle">First Line:</td>
				<td><sf:input class="control firstLine" type="text" name="content" id="firstLine"
						path="content" /> <br />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<div class="errorMessage">
						<p><sf:errors path="content"></sf:errors></p>
						<p id="remaining"></p>
					</div>
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td>
					<p class="errorMessage"><c:out value="${error}"></c:out></p>
				</td>
			</tr>
			<tr>
				<td><input class="submitButton" type="submit" value="Create" /></td>
		</table>
	</sf:form>
	<c:if test="${error == 'You must be logged in to start a story.'}"><a href="<c:url value="/login"/>">log in</a></c:if>

<div class="indented moreSpaceAbove spaceBelow">
	<p class="link"><a href="<c:url value="/"/>">Go Home</a></p>
</div>



<script type="text/javascript">

	//check for if the page was reloaded, after sumbitting, but received an error
	if($('#firstLine').val().length > 0) {
		$('#remaining').html(("You have written "+$('#firstLine').val().length)+" characters.");
	}
	//dynamically displays the amount of characters they have written.
	$(document).on("keyup", "#firstLine", function() {
			$('#remaining').html(("You have written "+$('#firstLine').val().length)+" characters.");			
	});
</script>

</body>
</html>	