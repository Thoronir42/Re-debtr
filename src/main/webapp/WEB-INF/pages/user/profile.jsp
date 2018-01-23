<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>

<div class="card mb-3">
    <div class="card-body">
        <h1>${requestScope.profile.firstName} ${requestScope.profile.lastName}</h1>
    </div>
</div>

<div class="row">
    <div class="col-md-4">
        <div class="card">
            <div class="card-header profile-section-title">
                Connections (${requestScope.contacts.size()})
            </div>
            <div class="card-body contact-badges text-center">
                <c:forEach items="${requestScope.contacts}" var="cp">
                    <%--@elvariable id="cp" type="cz.zcu.students.kiwi.redebtr.model.UserProfile"--%>
                    <ctrl:profile-badge profile="${cp}"/>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="card mb-2">
            <div class="card-body p-2">
                <ctrl:create-post targetDashboard="${requestScope.profile.locator}"/>
            </div>
        </div>
        <c:forEach items="${requestScope.posts}" var="post">
            <ctrl:dash-post post="${post}">
                DASH
            </ctrl:dash-post>
        </c:forEach>
    </div>
</div>
