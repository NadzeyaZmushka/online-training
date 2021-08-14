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
    <link rel="stylesheet" href="css/course.css">
</head>
<body>
<div class="container-fluid bg">
    <ctg:currentTime/>
    <c:import url="fragment/header.jsp"/>
    <c:import url="fragment/sidebar.jsp"/>

    <h1 style="text-align: center"><fmt:message key="main.itCourse"/></h1>
    <table class="table">
        <tr>
            <th><fmt:message key="main.our.courses"/> </th>
        </tr>
    </table>
    <c:forEach var="course" items="${courses}">
        <tr>
            <td>
                <div style="text-align: center">
                    <a
                            href="${pageContext.request.contextPath}/controller?command=course_page&course_id=${course.id}">
                        <p> ${course.name}</p>
                    </a></div>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${user.getRole().toString() eq 'ADMIN'}">
        <div style="text-align: right">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="course_add">
                <input type="text" name="course_name" placeholder="<fmt:message key="course.name"/>"
                       value="${course_name}" required pattern=".*[^<>]">
                <c:if test="${errorIsNotValidCourseName}">
                    <h3><fmt:message key="error.main.is_not_valid_course_name"/></h3>
                </c:if>
<%--                <c:if test="${errorCourseNotFound}">--%>
<%--                    <h3><fmt:message key="error.main.course_not_found"/></h3>--%>
<%--                </c:if>--%>
                <input type="text" name="description" placeholder="<fmt:message key="course.description"/>"
                       value="${description}" required pattern=".*[^<>]">
                <c:if test="${errorDescriptionAdd}">
                    <fmt:message key="error.course.description"/>
                </c:if>
                <input type="text" name="hours" placeholder="<fmt:message key="course.number_of_hours"/>"
                       value="${hours}"
                       required pattern="\d+">
                <c:if test="${errorHoursAdd}">
                    <fmt:message key="error.course.hours"/>
                </c:if>
                <input type="text" name="cost" placeholder="<fmt:message key="course.cost"/>" value="${cost}"
                       required pattern="\d+[.]\d{2,3}">
                <c:if test="${errorCostAdd}">
                    <fmt:message key="error.course.cost"/>
                </c:if>
                <input type="text" name="name" placeholder="Teacher name" value="${name}" required
                       pattern="^[A-za-z-]{3,16}$">
                <input type="text" name="surname" placeholder="Teacher surname" value="${surname}" required
                       pattern="^[A-za-z-]{3,16}$">
                <c:if test="${errorNameAndSurnameAdd}">
                    <h3><fmt:message key="error.course.name_and_surname"/></h3>
                </c:if>
                <c:if test="${errorTeacherNotFoundAdd}">
                    <h3><fmt:message key="error.course.teacher_not_found"/></h3>
                </c:if>
                <br>
                <fmt:message key="course.start_course"/>
                <input type="date" name="start_course" value="${startCourse}"
                       placeholder="<fmt:message key="course.start_course"/>" required>
                <fmt:message key="course.end_course"/>
                <input type="date" name="end_course" value="${endCourse}"
                       placeholder="<fmt:message key="course.end_course"/>" required>
                <c:if test="${errorStartAndEndCourseAdd}">
                    <h3><fmt:message key="error.course.start_and_end_course"/></h3>
                </c:if>
                <input type="submit" class="header_button" value="<fmt:message key="button.add_course"/>">
            </form>
        </div>
    </c:if>
    <br>
    <c:import url="fragment/footer.jsp"/>
</div>
</body>
</html>
