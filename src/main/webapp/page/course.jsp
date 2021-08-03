<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="main.itCourse"/></title>

    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="container-fluid bg_course">
<c:import url="header.jsp"/>
<c:import url="sidebar.jsp"/>
<c:if test="${course != null}">

    <h2>${course.getName()}</h2>
    <p><fmt:message key="p.course.description"/>: ${course.getDescription()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_description">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="description" value="${course.getDescription()}" required>
            <input type="submit" value="<fmt:message key="button.course.update_description"/>">
        </form>
    </c:if>
    <p><fmt:message key="p.course.hours"/>: ${course.getHours()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_hours">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="hours" value="${course.getHours()}" required pattern="\d+">
            <input type="submit" value="<fmt:message key="button.course.update_hours"/>">
        </form>
    </c:if>
    <p><fmt:message key="p.course.start_course"/>: ${course.getStartCourse()}</p>
    <p><fmt:message key="p.course.end_course"/>: ${course.getEndCourse()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_start_end">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="date" name="start" value="${course.getStartCourse()}" required>
            <input type="date" name="end" value="${course.getEndCourse()}" required>
            <input type="submit" value="<fmt:message key="button.course.update_start_end"/>">
        </form>
    </c:if>
    <p><fmt:message key="p.course.teacher"/>: ${course.getTeacher()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_teacher">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="teacher_name" value="${course.getTeacher().getName()}" required
                   pattern="^[\p{L}]+$">
            <input type="text" name="teacher_surname" value="${course.getTeacher().getSurname()}" required
                   pattern="^[\p{L}]+$">
            <input type="submit" value="<fmt:message key="button.course.update_teacher"/>">
        </form>
    </c:if>
    <p><fmt:message key="p.course.cost"/>: ${course.getCost()}</p>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="update_cost">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="text" name="cost" value="${course.getCost()}" required pattern="\d+">
            <input type="submit" value="<fmt:message key="button.course.update_cost"/>">
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="course_update">
            <input type="hidden" name="course_id" value="${courseId}">
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
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="submit" value="<fmt:message key="button.delete_course"/>">
        </form>
    </c:if>
    </c:if>
    <c:if test="${user.getRole().toString() eq 'USER'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="enroll_course">
            <input type="hidden" name="course_id" value="${courseId}">
            <input type="submit" value="<fmt:message key="button.submit.course"/>">
        </form>
    </c:if>
    <h3><fmt:message key="title.lecture"/></h3>
    <c:if test="${lectures != null}">
        <c:forEach var="lecture" items="${lectures}">
            <li>${lecture.getName()}</li>
            <c:if test="${user.getRole().toString() eq 'ADMIN'}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="lecture_update">
                    <input type="hidden" name="lecture_id" value="${lecture.getId()}">
                    <input type="hidden" name="course_id" value="${courseId}">
                    <input type="text" name="lecture" value="${lecture.getName()}" required>
                    <input type="submit" value="<fmt:message key="button.update_lecture"/>">
                </form>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="lecture_delete">
                    <input type="hidden" name="lecture_id" value="${lecture.getId()}">
                    <input type="hidden" name="course_id" value="${courseId}">
                    <input type="submit" value="<fmt:message key="button.delete_lecture"/>">
                </form>
            </c:if>
        </c:forEach>
    </c:if>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="lecture_add">
            <input type="hidden" name="course_id" value="${course.getId()}">
            <input type="text" name="message" required>
            <input type="submit" value="<fmt:message key="button.add_lecture"/>">
        </form>
    </c:if>


    <c:if test="${lectures == null}">
        <h1><fmt:message key="no.lectures"/></h1>
    </c:if>

<c:import url="footer.jsp"/>
</div>
</body>
</html>
