<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>

<%@ tag description="User thumb" language="java" %>

<%@ attribute name="user" type="cz.zcu.students.kiwi.redebtr.model.UserProfile" required="true" %>
<%@ attribute name="size" %>
<%@ attribute name="date" type="java.util.Date" %>

<div class="user-thumbnail">
    <ctrl:profile-badge profile="${user}"/>
    <span class="name">${user.fullName}</span>
    <c:if test="${not empty date}">
        <br/>
        <small class="date-moment"><fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm"/></small>
    </c:if>
</div>

