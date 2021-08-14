<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customTag" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="title.contacts"/> </title>

    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="bg">
    <div style="text-align: center"><ctg:currentTime/></div>
    <c:import url="fragment/header.jsp"/>
    <c:import url="fragment/sidebar.jsp"/>
    <div style="text-align: left">
        <h4 style="font-style: italic"><fmt:message key="contacts.1"/></h4>
        <h4 style="font-style: italic"><fmt:message key="contacts.2"/></h4>
    </div>
    <c:import url="fragment/footer.jsp"/>
</div>
</body>
</html>
