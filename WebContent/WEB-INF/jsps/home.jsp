<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<sql:query var="rs" dataSource="jdbc/stories">
select username, password from authors
</sql:query>

  <h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
    Username: <c:out value="${row.username}"></c:out> <br />
    Password: ${row.password}<br/>
</c:forEach>


<a href="<c:url value="/completedStories"/>">Read the completed stories here</a>
<br />
<a href="<c:url value="/newStory"/>">Start a new story!</a>
<br />
<a href="<c:url value="/newContribution"/>">Add to an existing story!</a>
<br />
<a href="<c:url value="/login"/>">login</a>


</body>
</html>