<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag description="User thumb" language="java" %>

<%@ attribute name="user" type="cz.zcu.students.kiwi.redebtr.model.UserProfile" required="true" %>
<%@ attribute name="size" %>
<%@ attribute name="date" type="java.util.Date" %>

<div class="user-thumbnail">
    <div class="img-thumbnail">PA</div>
    <span class="name">${user.name}</span>
    <c:if test="${not empty date}">
        <br/>
        <small class="date-moment"><fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm"/></small>
    </c:if>
</div>

