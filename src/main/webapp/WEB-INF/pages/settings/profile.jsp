<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="bi" tagdir="/WEB-INF/tags/bs-inputs" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <jsp:include page="_header.jsp"/>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/settings/profile" method="post" enctype="multipart/form-data"
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

            <div class="form-group">
                <label class="form-control-label">Upload new avatar</label>
                <c:if test="${requestScope.profile.avatar != null}">
                    <a class="pull-right" href="${pageContext.request.contextPath}/settings/profile/remove-avatar">.. or
                        remove current</a>
                </c:if>
                <br/>

                <input type="file" name="avatar" id="customFile" class="form-control">
            </div>

            <div class="form-group text-right">
                <button type="submit" class="btn btn-primary">Update!</button>
            </div>
        </form>
    </div>
</div>
