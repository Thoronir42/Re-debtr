<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Header tag" pageEncoding="UTF-8" %>

<%@attribute name="user" type="cz.zcu.students.kiwi.redebtr.model.User" %>
<%@attribute name="authUser" type="cz.zcu.students.kiwi.libs.auth.AuthUser" %>

<nav class="navbar fixed-top navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">ReDebtr</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/profile/Karel">Karel</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="dropdown-divider hidden-md-up">
                <hr/>
            </li>
            <c:choose>
                <c:when test="${not authUser.loggedIn}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/sign/in">Sign in</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/user/profile/${user.profile.locator}">${user.profile.fullName}</a>
                    </li>
                    <li class="nav-item btn-group">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-cog"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li class="dropdown-item">
                                <a href="${pageContext.request.contextPath}/settings/profile">Settings</a>
                            </li>
                            <li class="dropdown-divider"></li>
                            <li class="dropdown-item"><a href="${pageContext.request.contextPath}/sign/out">Sign out</a>
                            </li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
