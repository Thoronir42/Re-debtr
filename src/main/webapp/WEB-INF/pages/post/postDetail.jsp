<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card card-post">
    <div class="card-header">
        <ctrl:post-header user="${requestScope.post.author}"
                          date="${requestScope.post.dateCreated}"/>
    </div>
    <div class="card-body">
        <p>${requestScope.post.message}</p>
    </div>
    <div class="card-footer">
        <a class="btn btn-sm btn-primary">
            <i class="fa"></i>
        </a>
    </div>
</div>

<div class="card dash-post mb-2">
    <div class="card-footer">
        <span>Comments</span>
        <div class="p-1">
            <c:forEach items="${requestScope.post.comments.iterator()}" var="comment">
                <ctrl:post-comment comment="${comment}"/>
            </c:forEach>
        </div>
    </div>
</div>
