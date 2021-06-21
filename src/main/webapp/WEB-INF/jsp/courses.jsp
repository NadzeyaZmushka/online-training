<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Список курсов:</h1>
<ul>
    <c:forEach var="course" items="${requestScope.courses}">
        <li>
            <a href="${pageContext.request.contextPath}/users?courseId=${course.id}">${course.name}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
