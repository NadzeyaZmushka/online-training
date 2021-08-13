<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customTag" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.profile"/></title>

    <link rel="stylesheet" href="css/profile.css">
</head>
<body>
<div class="bg_profile">
    <ctg:currentTime/>
    <c:import url="header.jsp"/>
    <c:import url="sidebar.jsp"/>

    <c:if test="${user == null}">
        <h1><fmt:message key="no.profile"/></h1>
    </c:if>
    <div style="text-align: right">
        <c:if test="${user.getRole().toString() eq 'ADMIN'}">
            <c:if test="${allTeachers == null}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="show_all_teachers">
                    <input type="submit" class="profile_button"
                           value="<fmt:message key="button.profile.show_all_teachers"/>">
                </form>
            </c:if>
            <c:if test="${allTeachers != null}">
                <table class="table">
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
                                    <input type="submit" class="profile_button"
                                           value="<fmt:message key="button.profile.teacher_delete"/>">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="hide_all_teachers">
                    <input type="submit" class="profile_button"
                           value="<fmt:message key="button.profile.hide_all_teachers"/>">
                </form>
            </c:if>
            <c:if test="${allTeachers != null}">
                <h1><fmt:message key="profile.add_teacher"/></h1>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="teacher_add">
                    <input type="text" name="teacher_name" required pattern="^[\p{L}]+$">
                    <input type="text" name="teacher_surname" required pattern="^[\p{L}]+$">
                    <input type="submit" class="profile_button" value="<fmt:message key="button.profile.add_teacher"/>">
                </form>
            </c:if>
            <c:if test="${allUsers == null}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="show_all_users">
                    <input type="submit" class="profile_button"
                           value="<fmt:message key="button.profile.show_all_users"/>">
                </form>
            </c:if>
            <c:if test="${allUsers != null}">
                <table class="table">
                    <tr>
                        <th><fmt:message key="table.profile.u_id"/></th>
                        <th><fmt:message key="table.profile.u_email"/></th>
                        <th><fmt:message key="table.profile.u_name"/></th>
                        <th><fmt:message key="table.profile.u_surname"/></th>
                        <th><fmt:message key="table.profile.u_role"/></th>
                        <th><fmt:message key="table.profile.u_status"/></th>
                        <th><fmt:message key="table.profile.create_admin"/></th>
                        <th><fmt:message key="table.profile.change_status"/></th>
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
                                    <input type="submit" class="profile_button"
                                           value="<fmt:message key="table.profile.create_admin"/>">
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${user.isEnabled() eq true}">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="block_user">
                                    <input type="hidden" name="user_id" value="${user.getId()}">
                                    <input type="submit" class="profile_button"
                                           value="<fmt:message key="profile.block_user"/>">
                                </form>
                            </c:if>
                            <c:if test="${user.isEnabled() eq false}">
                                <form>
                                    <input type="hidden" name="command" value="unblock_user">
                                    <input type="hidden" name="user_id" value="${user.getId()}">
                                    <input type="submit" class="profile_button"
                                           value="<fmt:message key="profile.unblock_user"/>">
                                </form>
                            </c:if>
                        </td>
                        </c:forEach>
                    </tr>
                </table>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="hide_all_users">
                    <input type="submit" class="profile_button"
                           value="<fmt:message key="button.profile.hide_all_users"/>">
                </form>
            </c:if>
            <c:if test="${usersEnrolledCourse == null}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="show_all_users_enrolled_course">
                    <input type="submit" class="profile_button_users"
                           value="<fmt:message key="button.profile.show_all_user_enrolled_course"/>">
                </form>
            </c:if>
            <c:if test="${usersEnrolledCourse != null}">
                <table class="table">
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
                    <input type="submit" class="profile_button_users"
                           value="<fmt:message key="button.profile.hide_all_users_enrolled_course"/>">
                </form>
            </c:if>
        </c:if>
    </div>
    <div style="text-align: left">
        <c:if test="${user.getRole().toString() eq 'USER'}">
            <c:if test="${userEnrolledByCourse != null}">
                <div style="text-align: center">
                    <h3><fmt:message key="profile.your_courses"/></h3>
                    <table class="table">
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
                </div>
            </c:if>
        </c:if>
        <c:if test="${user != null}">
            <h3><fmt:message key="placeholder.email"/> - ${user.getEmail()}</h3>
            <h3><fmt:message key="placeholder.name"/> - ${user.getName()}</h3>
            <h3><fmt:message key="placeholder.surname"/> - ${user.getSurname()}</h3>
            <h3><fmt:message key="profile.rename_name_surname"/></h3>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="update_user_name_surname">
                <input type="text" name="name" value="${user.getName()}">
                <input type="text" name="surname" value="${user.getSurname()}" required pattern="^[\p{L}]+$">
                <input type="submit" class="profile_button"
                       value="<fmt:message key="button.profile.update_name_surname"/>">
            </form>
            <c:if test="${success}">
                <h3><fmt:message key="success"/></h3>
            </c:if>
            <h3><fmt:message key="profile.role"/> - ${user.getRole()}</h3>
            <h3><fmt:message key="button.profile.update_password"/></h3>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="update_password">
                <input type="password" name="password" required
                       pattern="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
                       title='<fmt:message key="input.title.password"/>'>
                <input type="password" name="repeat_password" required
                       pattern="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
                       title='<fmt:message key="input.title.password"/>'>
                <input type="submit" class="profile_button"
                       value="<fmt:message key="button.profile.update_password"/>">
            </form>
            <c:if test="${success}">
                <h3><fmt:message key="success"/></h3>
            </c:if>
            <br>
        </c:if>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
