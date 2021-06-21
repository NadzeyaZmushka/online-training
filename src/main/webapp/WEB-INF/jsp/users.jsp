<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty requestScope.users}">
    <h1>Студенты на курсе:</h1>
    <ul>
        <c:forEach var="user" items="${requestScope.users}">
            <li>${user.name}
                    ${user.surname}</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
