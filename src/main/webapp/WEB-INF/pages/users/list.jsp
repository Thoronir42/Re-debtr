<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctrl" tagdir="/WEB-INF/tags/controls" %>
<%@ taglib prefix="rd" uri="/WEB-INF/functions/helpersFunctions.taglib.tld" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="card">
    <c:if test="${not empty requestScope.listCaption}">
        <div class="card-header">
            ${requestScope.listCaption}
        </div>
    </c:if>
    <div></div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/users/search" method="get">
            <div class="form-group">
                <label class="form-control-label">Search users:</label>
                <div class="input-group">
                    <input name="q" class="form-control" title="Search" value="${param.q}">
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-info">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
        </form>

        <div class="row">
            <c:forEach var="p" items="${requestScope.profiles}">
                <%--@elvariable id="p" type="cz.zcu.students.kiwi.redebtr.model.UserProfile"--%>
                <div class="col-sm-6 mb-2">
                    <div class="profile-row">
                        <div class="badge-wrapper">
                            <ctrl:profile-badge profile="${p}"/>
                        </div>
                        <div class="details-wrapper">
                            <a href="${rd:profileLink(pageContext.request, p)}">${p.fullName}</a>
                            <br/>
                            <small>${p.locator}</small>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
