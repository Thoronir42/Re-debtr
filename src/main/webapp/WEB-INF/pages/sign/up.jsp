<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty requestScope.err}">
    Error: ${requestScope.err}
</c:if>

<div class="card">
    <div class="card-header">
        <h1>Registration</h1>
    </div>
    <div class="card-body">
        <form action="${baseUrl}/sign/up" method="post">
            <fieldset>
                <legend>Account info</legend>
                <div class="form-group">
                    <label class="form-control-label" for="username">Username:</label>
                    <input class="form-control" type="text" id="username" name="username" value="${param.username}">
                </div>
                <div class="form-group">
                    <label class="form-control-label" for="email">Email</label>
                    <input name="email" type="email" id="email" class="form-control" value="${param.email}">
                </div>
                <div class="form-group">
                    <label class="form-control-label" for="pwd">Password:</label>
                    <input class="form-control" type="password" id="pwd" name="password">
                </div>
                <div class="form-group">
                    <label class="form-control-label" for="confirm-pwd">Confirm Password:</label>
                    <input class="form-control" type="password" id="confirm-pwd" name="confirmPwd">
                </div>
            </fieldset>

            <fieldset>
                <legend>User Profile</legend>
                <div class="form-group">
                    <label class="form-control-label" for="profile-name">Name</label>
                    <input class="form-control" type="text" name="profileName" id="profile-name"
                           value="${param.profileName}">
                </div>
                <div class="form-group">
                    <label class="form-control-label" for="profile-surname">Surname</label>
                    <input name="profileSurname" type="text" id="profile-surname"
                           class="form-control" value="${param.profileSurname}">
                </div>
                <div class="form-group">
                    <label class="form-control-label" for="age">Age</label>
                    <input name="age" type="number" id="age"
                           class="form-control" min="15" value="${param.age}">
                </div>
            </fieldset>

            <div class="form-group text-center">
                <label>
                    <input type="checkbox" name="tosAgree" required<c:if test="${not empty param['tosAgree']}"> checked</c:if>>
                    <span>I have read and agree to the
                        <a href="${pageContext.request.contextPath}/terms-of-service">Terms of service</a>
                        <small>(last changed on 20.1.2018)</small>.
                    </span>
                </label>
                <input class="btn btn-primary" type="submit" value="Register">
            </div>
        </form>
    </div>
</div>

</body>
</html>
