<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="ctg" uri="customTag" %>--%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<header>
    <nav style="float: right;">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div style="display: flex; flex-grow: inherit; justify-content: flex-end">
                <form action="${pageContext.request.contextPath}/controller"
                      method="post">
                    <input type="hidden" name="command" value="change_language"/>
                    <div>
                        <fmt:message key="header.button.language"/>
                        <div>
                            <button type="submit" name="language" value="en">EN</button>
                            <button type="submit" name="language" value="ru">RU</button>
                        </div>
                    </div>
                </form>

                <c:if test="${requestScope.users == null}">
                    <form action="${pageContext.request.contextPath}/controller"
                          method="get">
                        <input type="hidden" name="command" value="sign_in_page">
                        <button type="submit">
                            <fmt:message key="button.signIn"/>
                        </button>
                    </form>
                </c:if>

                <c:if test="${requestScope.users == null}">
                    <form action="${pageContext.request.contextPath}/controller"
                          method="get">
                        <input type="hidden" name="command" value="sign_up_page">
                        <button type="submit">
                            <fmt:message key="button.signUp"/>
                        </button>
                    </form>
                </c:if>

                <c:if test="${user != null}">
                    <ctg:headertag role="${user.getEmail()}"/>
                </c:if>

                <c:if test="${user != null}">
                    <form action="${pageContext.request.contextPath}/controller"
                          method="post">
                        <input type="hidden" name="command" value="sign_out">
                        <button type="submit">
                            <fmt:message key="header.button.signOut"/>
                        </button>
                    </form>
                </c:if>
            </div>
        </div>
    </nav>
</header>
