<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Header tag" pageEncoding="UTF-8" %>

<%@attribute name="user" type="cz.zcu.students.kiwi.libs.security.IUser" %>

<nav class="navbar fixed-top navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">ReDebtr</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/profile?u=Karel">User profile</a></li>
        </ul>
        <ul class="navbar-nav">
            <li class="dropdown-divider hidden-md-up"><hr/></li>
            <li class="nav-item float-right">
                <c:choose>
                    <c:when test="${user.loggedIn}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/sign/in">Sign in</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="${pageContext.request.contextPath}/sign/out">Sign out</a>
                    </c:otherwise>
                </c:choose>

            </li>
        </ul>
    </div>
</nav>
