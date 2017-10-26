<%@ tag description="Dashboard view of a post" pageEncoding="UTF-8" %>
<%@ attribute name="post" type="cz.zcu.students.kiwi.redebtr.model.Post" required="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>

<div class="card dash-post">
    <div class="card-header">
        <span class="img-thumbnail">PA</span>
        <span class="post-author">${post.author.name}</span>
        <br/>
        <small class="date-moment"><fmt:formatDate value="${post.dateCreated}" pattern="yyyy-MM-dd HH:mm"/></small>
    </div>
    <div class="card-body">
        <p>${post.message}</p>
    </div>
    <div class="card-footer">
        <span>Comments</span>
        <div class="p-1">
            <c:forEach items="${post.comments.iterator()}" var="comment">
                <ctrl:post-comment comment="${comment}"/>
            </c:forEach>
        </div>
    </div>
</div>
