<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ tag description="Dashboard view of a psot" pageEncoding="UTF-8" %>
<%@ attribute name="post" type="cz.zcu.students.kiwi.redebtr.model.Post" %>

<div class="card">
    <div class="card-header">
        <span class="img-thumbnail">PA</span>
        <span class="post-author">${post.author.name}</span>
        <br/>
        <small><fmt:formatDate value="${post.dateCreated}" pattern="yyyy-MM-dd HH:mm"/></small>
    </div>
    <div class="card-body">
        <p>${post.message}</p>
    </div>
</div>
