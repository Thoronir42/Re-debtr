<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>

<%@ tag description="Comment belonging to a post" pageEncoding="UTF-8" %>
<%@ attribute name="comment" type="cz.zcu.students.kiwi.comments.Comment" required="true" %>

<div class="card post-comment mb-2">
    <div class="card-header">
        <ctrl:profile-badge profile="${comment.author}"/>
        <span class="post-author">${comment.author.fullName}</span>
        <br/>
        <small class="date-moment"><fmt:formatDate value="${comment.dateCreated}" pattern="yyyy-MM-dd HH:mm"/></small>
    </div>
    <div class="card-body">${comment.text}</div>
</div>
