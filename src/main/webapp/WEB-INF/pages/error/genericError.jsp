<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="jumbotron">
    <h1>Error ${requestScope.errorStatusCode}<c:if test="${not empty requestScope.errorName}">: ${requestScope.errorName}</c:if></h1>

    <p>During processing of <b>${requestScope.requestedUri}</b></p>

    <p>Please try again later or <a href="${pageContext.request.contextPath}/">go back to homepage</a>.</p>

    <c:if test="${not empty requestScope.stackTrace}">
    <code>${requestScope.stackTrace}</code>
    </c:if>
</div>


