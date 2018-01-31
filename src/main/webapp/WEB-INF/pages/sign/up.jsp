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
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="form-control-label required" for="username">Username</label>
                            <input class="form-control" type="text" id="username" name="username"
                                   value="${param.username}">
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="form-group">
                            <label class="form-control-label required" for="email">Email</label>
                            <input name="email" type="email" id="email" class="form-control" value="${param.email}">
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="form-control-label required" for="pwd">Password</label>
                            <input class="form-control" type="password" id="pwd" name="password">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="form-control-label required" for="confirm-pwd">Confirm Password</label>
                            <input class="form-control" type="password" id="confirm-pwd" name="confirmPwd">
                        </div>
                    </div>
                </div>
            </fieldset>

            <fieldset>
                <legend>User Profile</legend>
                <div class="row">
                    <div class="col-md-5">
                        <div class="form-group">
                            <label class="form-control-label required" for="profile-name">Name</label>
                            <input class="form-control" type="text" name="profileName" id="profile-name"
                                   value="${param.profileName}">
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="form-group ">
                            <label class="form-control-label required" for="profile-surname">Surname</label>
                            <input name="profileSurname" type="text" id="profile-surname"
                                   class="form-control" value="${param.profileSurname}">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group ">
                            <label class="form-control-label" for="age">Age</label>
                            <input name="age" type="number" id="age"
                                   class="form-control" min="15" value="${param.age}">
                        </div>
                    </div>
                    <div class="col-md-6 offset-md-2">
                        <div class="form-group">
                            <label class="form-control-label" for="human-test">Human verification. Robots, go awai
                                plz</label>
                            <input name="humanAnswer" type="text" id="human-test"
                                   class="form-control">
                            <p class="form-text">${requestScope.question.prompt}</p>
                        </div>
                    </div>
                </div>
            </fieldset>

            <div class="form-group text-center">
                <label class="custom-control custom-checkbox mb-0">
                    <input type="checkbox" name="tosAgree" class="custom-control-input"
                           required<c:if test="${not empty param['tosAgree']}"> checked</c:if>>
                    <span class="custom-control-description">I have read and agree to the
                        <a href="${pageContext.request.contextPath}/terms-of-service">Terms of service</a>
                        <small>(last changed on 20.1.2018)</small>.
                    </span>
                    <span class="custom-control-indicator"></span>
                </label>
            </div>
            <div class="form-group text-center">
                <input class="btn btn-primary" type="submit" value="Register">
            </div>
        </form>
    </div>
</div>

</body>
</html>
