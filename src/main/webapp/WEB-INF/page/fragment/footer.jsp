<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>
<footer>
    <link rel="stylesheet" href="css/footer.css">
<%--    <link rel="stylesheet" href="css/header.css">--%>
    <div style="align-content: end">
        <div style="text-align: center">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="change_language"/>
                <fmt:message key="footer.button.language"/>
                <button class="button_language" name="language" value="en">EN</button>
                <button class="button_language" name="language" value="ru">RU</button>
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="contacts_page"/>
                <input type="submit" class="cont_button" value="<fmt:message key="contacts" />"/>
            </form>
            <ctg:footerTag/>
        </div>
        <div style="display: flex; justify-content: space-around">

        </div>
    </div>
</footer>
