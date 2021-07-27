<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.about_us"/> </title>
    <c:import url="header.jsp"/>
    <c:import url="sidebar.jsp"/>
</head>
<body>
<div style="text-align: center">
    <h1><fmt:message key="title.about_us"/> </h1>
    <h3><fmt:message key="about_us"/> </h3>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
