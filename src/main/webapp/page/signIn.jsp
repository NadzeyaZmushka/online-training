<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="login"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="sidebar.jsp"/>
<div>
    <form action="${pageContext.request.contextPath}/controller?command=sign_in" method="post">
    <input type="hidden" name="command" value="sign_in">
    <label for="email"></label><input type="text" id="email" name="email" placeholder="<fmt:message key="placeholder.email" />" value="${email}" required>
    <label for="password"></label><input type="password" id="password" name="password" placeholder="<fmt:message key="placeholder.password"/> " required>
    <input type="submit" value="<fmt:message key="button.signIn"/> ">
</form>
<%--    <form action="${pageContext.request.contextPath}/controller" method="get">--%>
<%--        <input type="hidden" name="command" value="main_page">--%>
<%--        <input type="submit" value="Главная страница">--%>
<%--    </form>--%>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
