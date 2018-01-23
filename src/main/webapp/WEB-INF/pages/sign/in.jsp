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

            <div class="form-group text-center">
                <div class="input-group">
                    <div class="input-group-btn">
                        <input class="btn btn-primary" type="submit" value="Login">
                    </div>
                    <div class="input-group-addon">
                        <input type="checkbox" name="forgetMeNot" id="forgetMeNot">
                        <label class="mb-0" for="forgetMeNot">Stay signed in</label>
                    </div>

                </div>

            </div>
        </form>
    </div>
</div>
