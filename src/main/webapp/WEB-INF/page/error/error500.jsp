<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>ERROR</h1>
<h2>Something goes wrong...</h2>

<br/> Request from: ${pageContext.errorData.requestURI} is failed
<br/> Status code: ${pageContext.errorData.statusCode}
<br/> Servlet name: ${pageContext.errorData.servletName}
<br/> Exception: ${pageContext.exception}
<br/> Message from exception: ${pageContext.exception.message}
<br/> ${pageContext.exception.printStackTrace()}
<div>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="submit" value="To main page">
        <input type="hidden" name="command" value="main_page">
    </form>
</div>
</body>
</html>