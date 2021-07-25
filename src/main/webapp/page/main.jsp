<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customTag" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
<ctg:currentTime/>
<c:import url="header.jsp"/>
<c:import url="sidebar.jsp"/>

<h1 style="text-align: center"><fmt:message key="main.itCourse"/></h1>

<div>
    <c:forEach var="course" items="${courses}">
        <a href="${pageContext.request.contextPath}/controller?command=course_page&course_id=${course.id}">
            <p> ${course.name}</p>
        </a>
    </c:forEach>
</div>
<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="course_add">
        <p><fmt:message key="course.name"/></p>
        <label>
            <input type="text" name="course_name" placeholder="<fmt:message key="course.name"/>" value="${course_name}"
                   required>
        </label>
        <p><fmt:message key="course.description"/></p>
        <label>
            <input type="text" name="description" placeholder="<fmt:message key="course.description"/>"
                   value="${description}" required>
        </label>
        <c:if test="${errorDescriptionAdd}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="error.course.description"/>
            </div>
        </c:if>
        <p><fmt:message key="course.number_of_hours"/></p>
        <label>
            <input type="text" name="hours" placeholder="<fmt:message key="course.number_of_hours"/>" value="${hours}"
                   required>
        </label>
        <c:if test="${errorHoursAdd}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="error.course.hours"/>
            </div>
        </c:if>
        <p><fmt:message key="course.cost"/></p>
        <label>
            <input type="text" name="cost" placeholder="<fmt:message key="course.cost"/>" value="${cost}" required>
        </label>
        <c:if test="${errorCostAdd}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="error.course.cost"/>
            </div>
        </c:if>
        <p><fmt:message key="course.teacher_name"/></p>
        <label>
            <input type="text" name="name" placeholder="Teacher name" value="${name}" required pattern="^[\p{L}]+$">
        </label>
        <label>
            <input type="text" name="surname" placeholder="Teacher surname" value="${surname}" required
                   pattern="^[\p{L}]+$">
        </label>
        <c:if test="${errorNameAndSurnameAdd}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="error.course.name_and_surname"/>
            </div>
        </c:if>
        <c:if test="${errorTeacherNotFoundAdd}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="error.course.teacher_not_found"/>
            </div>
        </c:if>
        <p><fmt:message key="course.start_course"/></p>
        <label>
            <input type="date" name="start_course" value="${startCourse}"
                   placeholder="<fmt:message key="course.start_course"/>" required>
        </label>
        <p><fmt:message key="course.end_course"/></p>
        <label>
            <input type="date" name="end_course" value="${endCourse}"
                   placeholder="<fmt:message key="course.end_course"/>" required>
        </label>
        <c:if test="${errorStartAndEndCourseAdd}">
        <div class="alert alert-danger" role="alert">
                <fmt:message key="error.course.start_and_end_course"/>
                <%--            </div>--%>
            </c:if>
            <input type="submit" value="<fmt:message key="button.add_course"/>">
    </form>
</c:if>
<c:if test="${errorIsNotValidCourseName}">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="error.main.is_not_valid_course_name"/>
    </div>
</c:if>
<c:if test="${errorCourseNotFound}">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="error.main.course_not_found"/>
    </div>
</c:if>

<div class="modal-footer">
    <c:if test="${errorIsNotValidCourseName}">
        <button type="button" class="btn btn-default" data-dismiss="modal"
                onclick="<c:remove var="errorIsNotValidCourseName" scope="session"/>">Ок
        </button>
    </c:if>
    <c:if test="${errorCourseNotFound}">
        <button type="button" class="btn btn-default" data-dismiss="modal"
                onclick="<c:remove var="errorCourseNotFound" scope="session"/>">Ок
        </button>
    </c:if>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
