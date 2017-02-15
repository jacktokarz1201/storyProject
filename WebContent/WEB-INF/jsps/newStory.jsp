<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Start a new story</h2>

	<sf:form method="POST" action="${pageContext.request.contextPath}/createStory"
	commandName="story">
		<table>
			<tr>
				<td class="formTitle">Title:</td>
				<td><sf:input class="control" type="text" name="title"
						path="title" /> <br />
					<div class="errorMessage">
						<sf:errors path="title"></sf:errors>
					</div></td>
				<td class="formTitle">Characters per Line:</td>
				<td><sf:input class="control" type="number" name="lineLength"
						min="1" max="100" path="lineLength" /> <br />
					<div class="errorMessage">
						<sf:errors path="lineLength"></sf:errors>
					</div></td>
				<td class="formTitle">Total Lines:</td>
				<td><sf:input class="control" type="number" name="storyLength"
						min="2" max="100" path="storyLength" /> <br />
					<div class="errorMessage">
						<sf:errors path="storyLength"></sf:errors>
					</div></td>
					<td class="formTitle">First Line:</td>
				<td><sf:input class="control" type="text" name="content"
						path="content" /> <br />
					<div class="errorMessage">
						<sf:errors path="content"></sf:errors>
					</div></td>

				<td><input class="button" type="submit" value="Create" /></td>
		</table>
	</sf:form>
	<br />
	<c:out value="${error}"></c:out>
