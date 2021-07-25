<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<div>
    <nav style="float: end;">

        <%--        <form action="${pageContext.request.contextPath}/controller" method="get">--%>
        <%--            <input type="hidden" name="command" value="main_page">--%>
        <%--            <input type="submit" value=<fmt:message key="sidebar.path.main"/>>--%>
        <%--        </form>--%>
        <%--        <form action="${pageContext.request.contextPath}/controller" method="get">--%>
        <%--            <input type="hidden" name="command" value="profile_page">--%>
        <%--            <input type="submit" value=<fmt:message key="sidebar.path.profile"/>>--%>
        <%--        </form>--%>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=profile_page">
                <fmt:message key="sidebar.path.profile"/> </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=about_us_page">
                <fmt:message key="sidebar.path.aboutUs"/> </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=review_page">
                <fmt:message key="sidebar.path.review"/> </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=main_page"> <fmt:message
                    key="sidebar.path.main"/> </a>
        </li>
    </nav>
</div>
