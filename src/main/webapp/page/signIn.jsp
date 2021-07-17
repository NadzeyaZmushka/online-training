<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.sign_in"/></title>
</head>
<body>
<c:import url="header.jsp"/>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_in">
            <label for="email"></label><input type="text" id="email" class="fadeIn second" name="email"
                                              placeholder="<fmt:message key="placeholder.email"/>" value="${email}"
                                              required>
            <label for="password"></label><input type="password" id="password" class="fadeIn third" name="password"
                                                 placeholder="<fmt:message key="placeholder.password"/>" required>
            <input type="submit" class="fadeIn fourth" value="<fmt:message key="button.signIn"/>">
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
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="forgot_password_page">
            <input type="submit" value="<fmt:message key="button.signIn.forgotPassword"/>">
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="sign_up_page">
            <input type="submit" value="<fmt:message key="button.signUp"/>">
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="main_page">
            <input type="submit" value="Главная страница">
        </form>
<c:import url="footer.jsp"/>
</body>
</html>
