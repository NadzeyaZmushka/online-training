<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="sidebar.jsp"/>
<c:if test="${not empty requestScope.lectures}">
    <h1><fmt:message key="title.lecture"/> </h1>
    <ul>
        <c:forEach var="lecture" items="${requestScope.lectures}">
            <li>${lecture.name}</li>
        </c:forEach>
    </ul>
</c:if>
<c:import url="footer.jsp"/>
</body>
</html>
