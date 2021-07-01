<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<h1 style="text-align: center"><fmt:message key="main.itCourse"/></h1>

<div>
    <ul>
        <c:forEach var="course" items="${requestScope.courses}">
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=lecture_page&course_id=${course.id}">${course.name}</a>
            </li>
        </c:forEach>
    </ul>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
