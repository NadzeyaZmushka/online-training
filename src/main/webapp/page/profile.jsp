<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customTag" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="sidebar.jsp"/>

<c:if test="${user == null}">
    <h1><fmt:message key="no.profile"/></h1>
</c:if>

<c:if test="${user != null}">
    <h3><fmt:message key="placeholder.email"/> - ${user.getEmail()}</h3>
    <h3><fmt:message key="placeholder.name"/> - ${user.getName()}</h3>
    <h3><fmt:message key="placeholder.surname"/> - ${user.getSurname()}</h3>
    <h3><fmt:message key="profile.role"/> - ${user.getRole()}</h3>

    <div style="text-align: right">
        <c:if test="${user != null}">
            <h3><fmt:message key="profile.rename_name_surname"/></h3>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="update_user_name_surname">
                <input type="text" name="name" value="${user.getName()}" required pattern="^[\p{L}]+$">
                <input type="text" name="surname" value="${user.getSurname()}" required pattern="^[\p{L}]+$">
                <input type="submit" value="<fmt:message key="button.profile.update_name_surname"/>">
            </form>
        </c:if>
    </div>
    <div style="text-align: right">
        <c:if test="${user != null}">
            <h3><fmt:message key="button.profile.update_password"/></h3>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="update_password">
                <input type="password" name="password" required pattern="^[a-zA-Z0-9]+"
                       title='<fmt:message key="input.title.password"/>'>
                <input type="password" name="repeat_password" required pattern="^[a-zA-Z0-9]+"
                       title='<fmt:message key="input.title.password"/>'>
                <input type="submit" value="<fmt:message key="button.profile.update_password"/>">
            </form>
        </c:if>
    </div>
    <div style="text-align: right">
        <c:if test="${success}">
            <fmt:message key="success"/>
        </c:if>
    </div>
</c:if>
<c:if test="${user.getRole().toString() eq 'ADMIN'}">
    <c:if test="${allTeachers == null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="show_all_teachers">
            <input type="submit" value="<fmt:message key="button.profile.show_all_teachers"/>">
        </form>
    </c:if>
    <c:if test="${allTeachers != null}">
        <table border="1">
            <tr>
                <th><fmt:message key="table.profile.u_id"/></th>
                <th><fmt:message key="table.profile.u_name"/></th>
                <th><fmt:message key="table.profile.u_surname"/></th>
                <th><fmt:message key="profile.delete_teacher"/></th>

            </tr>
            <c:forEach items="${allTeachers}" var="teacher">
                <tr>
                    <td>${teacher.getId()}</td>
                    <td>${teacher.getName()}</td>
                    <td>${teacher.getSurname()}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="teacher_delete">
                            <input type="hidden" name="teacher_id" value="${teacher.getId()}">
                            <input type="submit" value="<fmt:message key="button.profile.teacher_delete"/>">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="hide_all_teachers">
            <input type="submit" value="<fmt:message key="button.profile.hide_all_teachers"/>">
        </form>
    </c:if>
    <c:if test="${allTeachers != null}">
        <h1><fmt:message key="profile.add_teacher"/></h1>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="teacher_add">
            <input type="text" name="teacher_name" required pattern="^[\p{L}]+$">
            <input type="text" name="teacher_surname" required pattern="^[\p{L}]+$">
            <input type="submit" value="<fmt:message key="button.profile.add_teacher"/>">
        </form>
    </c:if>
<%--    <c:if test="${allTeachers != null}">--%>
<%--        <h1><fmt:message key="profile.delete_teacher"/></h1>--%>
<%--        <form action="${pageContext.request.contextPath}/controller" method="post">--%>
<%--            <input type="hidden" name="command" value="teacher_delete">--%>
<%--            <c:forEach var="teacher" items="${allTeachers}">--%>
<%--                <input type="checkbox" name="teacher_id" value="${teacher.getId()}"/>${teacher.getId()}--%>
<%--            </c:forEach>--%>
<%--            <input type="submit" value="<fmt:message key="button.profile.teacher_delete"/>">--%>
<%--        </form>--%>
<%--    </c:if>--%>
    <c:if test="${allUsers == null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="show_all_users">
            <input type="submit" value="<fmt:message key="button.profile.show_all_users"/>">
        </form>
    </c:if>
    <c:if test="${allUsers != null}">
        <table border="1">
            <tr>
                <th><fmt:message key="table.profile.u_id"/></th>
                <th><fmt:message key="table.profile.u_email"/></th>
                <th><fmt:message key="table.profile.u_name"/></th>
                <th><fmt:message key="table.profile.u_surname"/></th>
                <th><fmt:message key="table.profile.u_role"/></th>
                <th><fmt:message key="table.profile.u_status"/></th>
                <th><fmt:message key="table.profile.create_admin"/></th>
            </tr>
            <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getName()}</td>
                <td>${user.getSurname()}</td>
                <td>${user.getRole()}</td>
                <td>
                    <c:if test="${user.isEnabled() == true}">
                        <fmt:message key="table.profile.u_status_active"/>
                    </c:if>
                    <c:if test="${user.isEnabled() == false}">
                        <fmt:message key="table.profile.u_status_inactive"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${user.getRole().toString() eq 'USER'}">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="update_to_admin">
                            <input type="hidden" name="user_id" value="${user.getId()}">
                            <input type="submit" value="<fmt:message key="table.profile.create_admin"/>">
                        </form>
                    </c:if>
                </td>
                </c:forEach>
            </tr>
        </table>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="hide_all_users">
            <input type="submit" value="<fmt:message key="button.profile.hide_all_users"/>">
        </form>
    </c:if>
    <c:if test="${usersEnrolledCourse == null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="show_all_users_enrolled_course">
            <input type="submit" value="<fmt:message key="button.profile.show_all_user_enrolled_course"/>">
        </form>
    </c:if>
    <c:if test="${usersEnrolledCourse != null}">
        <table border="1">
            <tr>
                <th><fmt:message key="table.profile.u_id"/></th>
                <th><fmt:message key="table.profile.u_email"/></th>
                <th><fmt:message key="table.profile.u_name"/></th>
                <th><fmt:message key="table.profile.u_surname"/></th>
                <th><fmt:message key="table.profile.th_course_id"/></th>
                <th><fmt:message key="table.profile.th_course_name"/></th>
            </tr>
            <c:forEach items="${usersEnrolledCourse}" var="userEnrolledCourse">
                <tr>
                    <td>${userEnrolledCourse.getId()}</td>
                    <td>${userEnrolledCourse.getEmail()}</td>
                    <td>${userEnrolledCourse.getName()}</td>
                    <td>${userEnrolledCourse.getSurname()}</td>
                    <td>${userEnrolledCourse.getCourse().getId()}</td>
                    <td>${userEnrolledCourse.getCourse().getName()}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${usersEnrolledCourse != null}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="hide_all_users_enrolled_course">
            <input type="submit" value="<fmt:message key="button.profile.hide_all_users_enrolled_course"/>">
        </form>
    </c:if>
</c:if>

<div style="text-align: left">
    <c:if test="${userEnrolledByCourse != null}">
        <h3><fmt:message key="profile.your_courses"/></h3>
        <table border="1">
            <tr>
                <th><fmt:message key="table.profile.th_course_name"/></th>
            </tr>
            <c:forEach var="course" items="${userEnrolledByCourse}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=course_page&course_id=${course.getId()}">
                                ${course.getName()}
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
