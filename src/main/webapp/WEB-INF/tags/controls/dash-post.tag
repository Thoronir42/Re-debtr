<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>

<%@ tag description="Dashboard view of a post" pageEncoding="UTF-8" %>
<%@ attribute name="post" type="cz.zcu.students.kiwi.redebtr.model.Post" required="true" %>

<div class="card dash-post mb-2">
    <div class="card-header">
        <ctrl:post-header post="${post}"/>
    </div>
    <div class="card-body">
        <%--<% getJspBody().invoke(getJspContext().getOut()); %> todo: possibly utilize body inserting --%>
        <p>${post.message}</p>
    </div>
    <div class="card-footer">
        <ctrl:reaction-rack/>
    </div>
    <div class="card-footer">
        <form action="${pageContext.request.contextPath}/post/${post.target.locator}/${post.id}/comment" method="post">
            <div class="input-group">
                <textarea name="text" class="form-control" placeholder="Write your comment"></textarea>

                <button type="submit" class="input-group-addon btn btn-outline-primary">Add comment</button>
            </div>



        </form>
        <div class="p-1">
            <c:forEach items="${post.comments.iterator()}" var="comment">
                <ctrl:post-comment comment="${comment}"/>
            </c:forEach>
        </div>
    </div>
</div>
