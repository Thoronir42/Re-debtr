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
            <div class="form-group">
                <label class="form-control-label" for="username">Username:</label>
                <input class="form-control" type="text" id="username" name="username">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="pwd">Password:</label>
                <input class="form-control" type="password" id="pwd" name="password">
            </div>
            <div class="form-group">
                <label class="form-control-label" for="confirmpwd">Confirm Password:</label>
                <input class="form-control" type="password" id="confirmpwd" name="confirmPwd">
            </div>
            <div class="form-group text-center">
                <input class="btn btn-primary" type="submit" value="Register">
            </div>
        </form>
    </div>
</div>

</body>
</html>
