<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card">
    <jsp:include page="_header.jsp"/>
    <div class="card-body">

        <h2>Delete account</h2>
        <p>When you gotta go, you gotta go...</p>
        <form action="${pageContext.request.contextPath}/settings/account/delete" method="post">
            <div class="form-group has-danger">
                <label class="form-control-label" for="delete-verification">Please type DELETE to confirm:</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="delete-verification"
                           name="deleteVerification" pattern="DELETE" required>
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-danger">Confirm</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
