<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>

<%@ tag description="User thumb" language="java" %>

<%@ attribute name="size" %>
<%@ attribute name="post" type="cz.zcu.students.kiwi.redebtr.model.Post" required="true" %>

<div class="user-thumbnail">
    <c:if test="${post.author ne post.target}">
        <ctrl:profile-badge profile="${post.author}"/>
        <span class="name">${post.author.fullName}</span>
        <span class="fa fa-chevron-right"></span>
    </c:if>
    <ctrl:profile-badge profile="${post.target}"/>
    <span class="name">${post.target.fullName}</span>
    <c:if test="${not empty post.dateCreated}">
        <br/>
        <small class="date-moment"><fmt:formatDate value="${post.dateCreated}" pattern="yyyy-MM-dd HH:mm"/></small>
    </c:if>
</div>

