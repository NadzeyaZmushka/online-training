<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<head>
    <title></title>
    <link rel="stylesheet" href="css/sidebar.css">
</head>
<nav style="float: end;">
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="main_page">
        <input type="submit" class="sidebar_button" value=<fmt:message key="sidebar.path.main"/>>
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="profile_page">
        <input type="submit" class="sidebar_button" value=<fmt:message key="sidebar.path.profile"/>>
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="about_us_page">
        <input type="submit" class="sidebar_button" value="<fmt:message key="sidebar.path.aboutUs"/>">
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="review_page">
        <input type="submit" class="sidebar_button" value="<fmt:message key="sidebar.path.review"/>">
    </form>
</nav>
