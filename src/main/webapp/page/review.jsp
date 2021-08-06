<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title><fmt:message key="title.review"/></title>

    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="container-fluid bg">
<c:import url="header.jsp"/>
<c:import url="sidebar.jsp"/>
<h1 style="text-align: center"><fmt:message key="title.review"/></h1>
<%--<div class="review-border">--%>
    <c:forEach var="review" items="${reviews}">
            <div class="review-border">${review.getUser().getName()} ${review.getUser().getSurname()} ${review.getDate().toString()}<br><br>
           "${review.getDescription()}"</div> <br/>
        <c:if test="${user.getRole().toString() eq 'ADMIN'}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="review_delete">
                <input type="hidden" name="review_id" value="${review.getId()}">
                <input type="submit" class="header_button" value="<fmt:message key="button.review.deleteReview"/>">
            </form>
        </c:if>
        <c:if test="${review.getUser().getEmail() eq user.getEmail()}">
            <c:if test="${user.getRole().toString() eq 'USER'}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="review_delete">
                    <input type="hidden" name="review_id" value="${review.getId()}">
                    <input type="submit" class="header_button" value="<fmt:message key="button.review.deleteReview"/>">
                </form>
            </c:if>
        </c:if>
    </c:forEach>
    <c:if test="${user == null}">
        <h1><fmt:message key="text.review"/></h1>
    </c:if>
    <c:if test="${user.getRole().toString() eq 'USER'}">
            <h1><fmt:message key="review.add"/></h1>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="review_add">
                <label>
                    <textarea name="message" required></textarea>
                </label>
                <input type="submit" class="header_button" value="<fmt:message key="button.review.sendReview"/>">
            </form>
    </c:if>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
