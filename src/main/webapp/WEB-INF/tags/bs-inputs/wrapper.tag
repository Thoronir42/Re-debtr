<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag %>

<%@ attribute name="label" %>

<%@ attribute name="error" %>

<div class="form-group<c:if test="${not empty error}"> has-error</c:if>">
    <c:if test="${not empty label}">
        <label class="form-control-label">${label}</label>
    </c:if>
    <% getJspBody().invoke(getJspContext().getOut()); %>
    <c:if test="${not empty error}">
        <p class="form-control-feedback">${error}</p>
    </c:if>
</div>
