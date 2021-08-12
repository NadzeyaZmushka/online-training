<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="ctg" uri="customTag" %>--%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Main</title>

    <link rel="stylesheet" href="css/header.css">

</head>
<body>
<nav style="float: right;">
    <c:if test="${user != null}">
        <h4><fmt:message key="hello"/>, "${user.getName()}"</h4>
    </c:if>
    <c:if test="${user == null}">
        <form action="${pageContext.request.contextPath}/controller"
              method="get">
            <input type="hidden" name="command" value="sign_in_page">
                <button class="header_button">
                    <fmt:message key="button.signIn"/>
                </button> </form>
        <form action="${pageContext.request.contextPath}/controller"
              method="get">
            <input type="hidden" name="command" value="sign_up_page">
            <button class="header_button">
                <fmt:message key="button.signUp"/>
            </button>
        </form>

    </c:if>
    <c:if test="${user != null}">
        <form action="${pageContext.request.contextPath}/controller"
              method="post">
            <input type="hidden" name="command" value="sign_out">
                <button class="header_button">
                    <fmt:message key="header.button.signOut"/>
                </button>
        </form>
    </c:if>
</nav>
</body>
</html>
