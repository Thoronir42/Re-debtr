<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="jumbotron">
    <h1>Error ${requestScope.errorStatusCode}: ${requestScope.errorName}</h1>

    <p>During processing of ${requestScope.requestedUri}</p>

    <p>Please try again later or <a href="${pageContext.request.contextPath}/">go back to homepage</a>.</p>
</div>


