<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customTag" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.main"/></title>

    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="container-fluid bg">
    <ctg:currentTime/>
    <c:import url="header.jsp"/>
    <c:import url="sidebar.jsp"/>

    <h1 style="text-align: center"><fmt:message key="main.itCourse"/></h1>
    <c:forEach var="course" items="${courses}">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=course_page&course_id=${course.id}">
                    <p> ${course.name}</p>
                </a>
            </li>
        </ul>
    </c:forEach>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <div style="text-align: right">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="course_add">
<%--                <p><fmt:message key="course.name"/></p>--%>
<%--                <label>--%>
                    <input type="text" name="course_name" placeholder="<fmt:message key="course.name"/>"
                           value="${course_name}"
                           required>
<%--                </label>--%>
<%--                <p><fmt:message key="course.description"/></p>--%>
<%--                <label>--%>
                    <input type="text" name="description" placeholder="<fmt:message key="course.description"/>"
                           value="${description}" required>
<%--                </label>--%>
                <c:if test="${errorDescriptionAdd}">
                    <fmt:message key="error.course.description"/>
                </c:if>
<%--                <p><fmt:message key="course.number_of_hours"/></p>--%>
<%--                <label>--%>
                    <input type="text" name="hours" placeholder="<fmt:message key="course.number_of_hours"/>"
                           value="${hours}"
                           required>
<%--                </label>--%>
                <c:if test="${errorHoursAdd}">
                    <fmt:message key="error.course.hours"/>
                </c:if>
<%--                <p><fmt:message key="course.cost"/></p>--%>
<%--                <label>--%>
                    <input type="text" name="cost" placeholder="<fmt:message key="course.cost"/>" value="${cost}"
                           required>
<%--                </label>--%>
                <c:if test="${errorCostAdd}">
                    <fmt:message key="error.course.cost"/>
                </c:if>
<%--                <p><fmt:message key="course.teacher_name"/></p>--%>
<%--                <label>--%>
                    <input type="text" name="name" placeholder="Teacher name" value="${name}" required
                           pattern="^[\p{L}]+$">
<%--                </label>--%>
<%--                <label>--%>
                    <input type="text" name="surname" placeholder="Teacher surname" value="${surname}" required
                           pattern="^[\p{L}]+$">
<%--                </label>--%>
                <c:if test="${errorNameAndSurnameAdd}">
                    <fmt:message key="error.course.name_and_surname"/>
                </c:if>
                <c:if test="${errorTeacherNotFoundAdd}">
                    <fmt:message key="error.course.teacher_not_found"/>
                </c:if>
                <br>
                <fmt:message key="course.start_course"/>
<%--                <label>--%>
                    <input type="date" name="start_course" value="${startCourse}"
                           placeholder="<fmt:message key="course.start_course"/>" required>
<%--                </label>--%>
<%--                <br>--%>
                <fmt:message key="course.end_course"/>
<%--                <label>--%>
                    <input type="date" name="end_course" value="${endCourse}"
                           placeholder="<fmt:message key="course.end_course"/>" required>
<%--                </label>--%>
                <c:if test="${errorStartAndEndCourseAdd}">
                    <fmt:message key="error.course.start_and_end_course"/>
                </c:if>
                <input type="submit" class="header_button" value="<fmt:message key="button.add_course"/>">
            </form>
        </div>
    </c:if>
    <c:if test="${errorIsNotValidCourseName}">
        <fmt:message key="error.main.is_not_valid_course_name"/>
    </c:if>
    <c:if test="${errorCourseNotFound}">
        <fmt:message key="error.main.course_not_found"/>
    </c:if>
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
<br>
<c:import url="footer.jsp"/>
</div>
</body>
</html>
