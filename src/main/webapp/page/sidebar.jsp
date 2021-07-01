<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<div>
    <nav style="float: end;">

        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="main_page">
            <input type="submit" value=<fmt:message key="title.main"/>>
        </form>
    </nav>
</div>
