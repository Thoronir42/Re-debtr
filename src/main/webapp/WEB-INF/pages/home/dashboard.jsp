<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>The dashboard</h1>

<c:forEach items="${requestScope.posts}" var="p">
    <ctrl:dash-post post="${p}"/>
</c:forEach>

