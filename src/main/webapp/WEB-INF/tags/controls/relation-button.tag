<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ attribute name="p" type="cz.zcu.students.kiwi.redebtr.model.UserProfile" required="true" %>

<div class="btn-group btn-group-sm pull-right">
    <c:if test="${p.contactStatus == null}">
        <a href="/user/${p.locator}/add-contact"
           class="btn btn-outline-info"
           data-toggle="tooltip" data-title="Add contact">
            <i class="fa fa-user-o"></i>
            <i class="fa fa-plus"></i>
        </a>
    </c:if>

    <%--Using enum values as strings is so gross, alas there is no other solution--%>
    <c:if test="${p.contactStatus eq 'Accepted'}">
        <a href="/user/${p.locator}/remove-contact"
           class="btn btn-primary"
           data-toggle="tooltip" data-title="Remove contact">
            <i class="fa fa-user-o"></i>
            <i class="fa fa-minus"></i>
        </a>
    </c:if>
    <c:if test="${p.contactStatus eq 'Received'}">
        <a href="/user/${p.locator}/add-contact"
           class="btn btn-secondary"
           data-toggle="tooltip" data-title="Accept contact">
            <i class="fa fa-user-o"></i>
            <i class="fa fa-chevron-left"></i>
        </a>
    </c:if>
    <c:if test="${p.contactStatus eq 'Requested'}">
        <a href="/user/${p.locator}/remove-contact"
           class="btn btn-outline-secondary"
           data-toggle="tooltip" data-title="Request sent">
            <i class="fa fa-user-o"></i>
            <i class="fa fa-chevron-right"></i>
        </a>
    </c:if>
</div>
