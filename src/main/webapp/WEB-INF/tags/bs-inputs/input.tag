<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bi" tagdir="/WEB-INF/tags/bs-inputs" %>

<%@ tag %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="type" %>
<%@ attribute name="value" %>

<%@ attribute name="error"%>
<%@ attribute name="label"%>


<bi:wrapper error="${error}"
            label="${label}">
    <input class="form-control"
           name="${name}"
           <c:if test="${not empty value}">value="${value}" </c:if>
           <c:if test="${not empty type}">type="${type}" </c:if>>
</bi:wrapper>

