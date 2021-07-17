<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="main.itCourse"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="sidebar.jsp"/>
<c:if test="${course != null}">
    <h2>${course.getName()}</h2>
    <p><fmt:message key="p.course.description"/>: ${course.getDescription()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="description_update">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="description" value="${course.getDescription()}" required>
            <input type="submit" value="<fmt:message key="button.course.update_description"/>">
        </form>
    </c:if>
    <p><fmt:message key="p.course.hours"/>: ${course.getHours()}</p>
    <p><fmt:message key="p.course.start_course"/>: ${course.getStartCourse()}</p>
    <p><fmt:message key="p.course.end_course"/>: ${course.getEndCourse()}</p>
    <p><fmt:message key="p.course.teacher"/>: ${course.getTeacher()}</p>

    <p><fmt:message key="course.cost"/>: ${course.getCost()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_cost">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="cost" value="${course.getCost()}" required pattern="\d+">
            <input type="submit" value="<fmt:message key="button.course.update_cost"/>">
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="course_update">
            <input type="hidden" name="course_id" value="${course.getId()}">
            <input type="text" name="course_name" value="${course.getName()}" required>
            <input type="submit" value="<fmt:message key="button.main.update_course"/>">
        </form>
        <c:if test="${errorIsNotValidCourseName}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="error.main.is_not_valid_course_name"/>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="course_delete">
        <input type="hidden" name="course_id" value="${course.getId()}">
        <input type="submit" value="<fmt:message key="button.delete_course"/>">
    </form>
    </c:if>
    <h3><fmt:message key="title.lecture"/> </h3>
    <ul>
        <c:forEach var="lecture" items="${requestScope.lectures}">
            <li>${lecture.name}</li>
        </c:forEach>
    </ul>
</c:if>
<c:import url="footer.jsp"/>
</body>
</html>
