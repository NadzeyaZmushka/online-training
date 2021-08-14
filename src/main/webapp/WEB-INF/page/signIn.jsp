<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.sign_in"/></title>

    <link rel="stylesheet" href="css/signIn.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="bg">
    <c:import url="fragment/header.jsp"/>
    <div class="signIn-container">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_in">
            <input type="email" id="email" name="email" placeholder="<fmt:message key="placeholder.email"/>"
                   value="${email}" required>
            <input type="password" id="password" name="password" placeholder="<fmt:message key="placeholder.password"/>"
                   required>
            <input type="submit" class="header_button" value="<fmt:message key="button.signIn"/>">
        </form>
        <c:if test="${requestScope.errorUserMessageIsValid}">
            <fmt:message key="error.signIn.emailAndPasswordIsValid"/>
        </c:if>
        <c:if test="${requestScope.errorUserBlock}">
            <fmt:message key="error.signIn.user_locked"/>
        </c:if>
        <c:if test="${requestScope.errorUserMessageIsNotExist}">
            <fmt:message key="error.signIn.emailAndPasswordIsIncorrect"/>
        </c:if>
        <%--    <form action="${pageContext.request.contextPath}/controller" method="get">--%>
        <%--        <input type="hidden" name="command" value="forgot_password_page">--%>
        <%--        <input type="submit" class="header_button" value="<fmt:message key="button.signIn.forgotPassword"/>">--%>
        <%--    </form>--%>
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
