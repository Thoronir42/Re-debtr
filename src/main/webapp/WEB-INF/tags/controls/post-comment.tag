<%@ tag description="Comment belonging to a post" pageEncoding="UTF-8" %>
<%@ attribute name="comment" type="cz.zcu.students.kiwi.comments.Comment" required="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card post-comment mb-2">
    <div class="card-header">
        <span class="img-thumbnail">PA</span>
        <span class="post-author">${comment.author.name}</span>
        <br/>
        <small class="date-moment"><fmt:formatDate value="${comment.dateCreated}" pattern="yyyy-MM-dd HH:mm"/></small>
    </div>
    <div class="card-body">${comment.text}</div>
</div>
