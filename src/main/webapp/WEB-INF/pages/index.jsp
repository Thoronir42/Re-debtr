<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>

<c:set var="baseUrl" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>ReDebtr<c:if test="${not empty requestScope.pageTitle}"> | ${requestScope.pageTitle}</c:if></title>
    <meta charset="UTF-8">
    <meta name="author" content="Kiwi">

    <link rel="stylesheet" type="text/css" href="${baseUrl}/resources/bs4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${baseUrl}/resources/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${baseUrl}/resources/css/main.css">
</head>
<body>
<layout:navbarTop user="${requestScope.user}" authUser="${requestScope.authUser}"/>
<div class="container" id="content-container">

    <c:if test="${requestScope.flashes.hasMessages()}">
        <c:forEach var="flash" items="${requestScope.flashes.dump()}" >
            <%--@elvariable id="flash" type="cz.zcu.students.kiwi.libs.FlashMessage"--%>
            <p class="alert alert-${flash.bsLevel}">
                    ${flash.text}
            </p>
        </c:forEach>
    </c:if>

    <c:choose>
        <c:when test="${not empty requestScope.pageTemplate}">
            <jsp:include page="/WEB-INF/pages/${requestScope.pageTemplate}"/>
        </c:when>
        <c:otherwise>
            <div>Something went wronk</div>
        </c:otherwise>
    </c:choose>
    <layout:footer/>
</div>

<script src="${baseUrl}/resources/jquery/jquery-3.2.1.min.js"></script>
<script src="${baseUrl}/resources/popper/popper.js"></script>
<script src="${baseUrl}/resources/bs4/js/bootstrap.min.js"></script>
<script src="${baseUrl}/resources/js/main.js"></script>

</body>
</html>
