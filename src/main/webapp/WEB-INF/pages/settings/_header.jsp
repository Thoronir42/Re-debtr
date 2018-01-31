<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card-header">
    <h1>${requestScope.caption}</h1>
    <ul class="nav nav-tabs card-header-tabs">
        <c:forEach items="${requestScope.sections}" var="link">
            <%--@elvariable id="link" type="cz.zcu.students.kiwi.redebtr.controllers.ASettingsController.Link"--%>
            <c:set var="linkClass" value=""/>
            <c:if test="${requestScope['javax.servlet.forward.request_uri'] eq link.link}">
                <c:set var="linkClass" value=" active"/>
            </c:if>

            <li class="nav-item${linkClass}">
                <a class="nav-link${linkClass}"
                   href="${pageContext.request.contextPath}${link.link}">${link.caption}</a>
            </li>
        </c:forEach>
    </ul>
</div>
