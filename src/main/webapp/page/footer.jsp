<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<footer>
<link rel="stylesheet" href="css/footer.css">
    <div style="align-content: end">
        <div style="text-align: center">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="change_language"/>
                <fmt:message key="footer.button.language"/>
                <button class="button_language" name="language" value="en">EN</button>
                <button class="button_language" name="language" value="ru">RU</button>
            </form>
            <ctg:footerTag/>
        </div>
        <div style="display: flex; justify-content: space-around">

        </div>
    </div>
</footer>
