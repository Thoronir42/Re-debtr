<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="bi" tagdir="/WEB-INF/tags/bs-inputs" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h1>User settings</h1>
        <ul class="nav nav-tabs card-header-tabs">
            <li class="nav-item active">
                <a class="nav-link active">Profile</a>
            </li>
            <li class="nav-item"><a class="nav-link">Shirt</a></li>
            <li class="nav-item"><a class="nav-link">Pants</a></li>
        </ul>
    </div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/settings/profile" method="post"
              class="bs-validation">
            <c:if test="${not empty requestScope['err.form']}">
                <div class="alert alert-danger">
                    <p>${requestScope['err.form']}</p>
                </div>
            </c:if>
            <bi:input name="firstName" label="First name"
                      value="${requestScope.profile.firstName}"
                      error="${requestScope['err.firstName']}"/>
            <bi:input name="lastName" label="Last name"
                      value="${requestScope.profile.lastName}"
                      error="${requestScope['err.lastName']}"/>
            <bi:input name="locator" label="Profile URL"
                      value="${requestScope.profile.locator}"
                      error="${requestScope['err.locator']}"/>
            <div class="form-group text-right">
                <button type="submit" class="btn btn-primary">Update!</button>
            </div>
        </form>
    </div>
</div>