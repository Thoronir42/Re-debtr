<%@ tag description="Post creation form" language="java" %>
<%@ attribute name="targetDashboard" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="postHelper" class="cz.zcu.students.kiwi.redebtr.helpers.PostHelper"/>


<c:set var="_action" value="${pageContext.request.contextPath}/post"/>
<c:if test="${not empty targetDashboard}">
    <c:set var="_action" value="${_action}?targetDashboard=${targetDashboard}"/>
</c:if>

<form action="${_action}" method="post">

    <div class="card">
        <div class="card-body">
            <div class="form-group">
                <span class="pb-1">
                    <span>Add a </span>
                    <span class="btn-group btn-group-sm" data-toggle="buttons">
                        <c:forEach items="${postHelper.types}" var="type" varStatus="i">
                            <label class="btn btn-outline-secondary<c:if test="${i.first}"> active</c:if><c:if test="${not i.first}"> disabled</c:if>">
                                <input type="radio"
                                       name="postType"
                                       value="${type.key.value}"
                                <c:if test="${i.first}"> checked</c:if>
                                <c:if test="${not i.first}"> disabled</c:if>>
                                <span>${type.value}</span>
                            </label>
                        </c:forEach>
                    </span>
                    <span>: </span>
                </span>
                <textarea class="form-control" name="text" title="Post content"></textarea>
            </div>
        </div>
        <div class="card-footer text-right">
            <input type="submit" class="btn btn-sm btn-primary">
        </div>
    </div>
</form>

