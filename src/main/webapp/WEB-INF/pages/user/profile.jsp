<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>

<div class="card">
    <div class="card-body">
        <h1>${requestScope.profile.firstName} ${requestScope.profile.lastName}</h1>
    </div>
</div>

<div class="row">
    <div class="col-md-4">
        <div class="card">
            <div class="card-header profile-section-title">
                Connections
            </div>
            <div class="list-group">
                <c:forEach items="${requestScope.profile.connections}" var="cp">
                    <div class="list-group-item">
                        <span>${cp.firstName} ${cp.lastName}</span>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="card">
            <div class="card-body">
                <c:forEach items="${requestScope.posts}" var="post">
                    <ctrl:dash-post post="${post}"></ctrl:dash-post>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
