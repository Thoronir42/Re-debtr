<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card">
    <div class="card-header">
        <a class="float-right" href="${baseUrl}/sign/up">Sign up</a>
        <h1>Login</h1>
    </div>
    <div class="card-body">
        <c:if test="${not empty requestScope.err}">
            <div class="alert alert-warning">${requestScope.err}</div>
        </c:if>
        <form action="${baseUrl}/sign/in" method="post">
            <div class="form-group">
                <label class="form-control-label" for="username">Login:&nbsp;</label>
                <input class="form-control" type="text" id="username" name="username" value="${param.username}">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="pwd">Password:&nbsp;</label>
                <input class="form-control" type="password" id="pwd" name="password">
            </div>

            <div class="form-group">
                <input class="btn btn-primary" type="submit" value="Login">
            </div>
        </form>
    </div>
</div>
