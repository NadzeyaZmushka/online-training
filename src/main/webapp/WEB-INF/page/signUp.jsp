<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.sign_up"/></title>

    <link rel="stylesheet" href="css/signIn.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="bg">
    <c:import url="fragment/header.jsp"/>
    <div class="signIn-container">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_up">
            <input type="email" id="email" name="email"
                   placeholder="<fmt:message key="placeholder.email"/>"
                   value="${requestScope.email}"
                   required pattern="^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}">
            <br><br>
            <c:if test="${requestScope.errorEmailMessageInvalid}">
                <br> <fmt:message key="error.signUp.emailInvalid"/><br>
            </c:if>
            <c:if test="${requestScope.errorEmailMessageIsExist}">
                <br> <fmt:message key="error.signUp.emailIsExist"/><br>
            </c:if>
            <input type="text" id="name" name="name" value="${requestScope.name}"
                   placeholder="<fmt:message key="placeholder.name"/>" required
                   pattern=".*[^<>]">
            <br><br>
            <input type="text" id="surname" name="surname" value="${requestScope.surname}"
                   placeholder="<fmt:message key="placeholder.surname"/>" required
                   pattern=".*[^<>]">
            <br><br>
            <c:if test="${requestScope.errorNameAndSurnameMessage}">
                <br> <fmt:message key="error.signUp.nameAndSurname"/><br>
            </c:if>
            <input type="password" id="password" name="password" placeholder="<fmt:message key="placeholder.password"/>"
                   required pattern="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
                   title='<fmt:message key="input.title.password"/>'>
            <br><br>
            <input type="password" id="repeat_password" name="repeat_password"
                   placeholder="<fmt:message key="placeholder.passwordRepeat"/>" required
                   pattern="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
                   title='<fmt:message key="input.title.password"/>'>
            <br><br>
            <c:if test="${requestScope.errorPasswordMessage}">
                <br> <fmt:message key="error.signUp.password"/><br>
            </c:if>
            <input type="submit" class="header_button" value="<fmt:message key="button.signUp"/>">
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="main_page">
            <input type="submit" class="header_button" value="<fmt:message
                    key="sidebar.path.main"/>">
        </form>
    </div>
    <c:import url="fragment/footer.jsp"/>
</div>
</body>
</html>
