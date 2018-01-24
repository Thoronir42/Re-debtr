<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="IpsumGenerator" class="cz.zcu.students.kiwi.temp.IpsumGenerator"/>
<c:set var="IpsumGenerator" value="${IpsumGenerator.createChild(2048)}"/>
<%--@elvariable id="IpsumGenerator" type="cz.zcu.students.kiwi.temp.IpsumGenerator"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card">
    <div class="card-body">
        <div class="row">
            <div class="col-md-9">
                <h1>ReDebtr</h1>
                <p>A social network which helps you with
                    <small class="text-muted">not forgetting</small>
                    your debts.
                </p>

                <h2>Most optimized debt-based social network*</h2>
                <p>${IpsumGenerator.paragraphs(2)}</p>
                <p>${IpsumGenerator.paragraphs(1)}</p>
                <h2>Cake is a lie</h2>
                <ul>
                    <li>Soul</li>
                    <li>Firstborn son</li>
                    <li>A coin</li>
                </ul>
            </div>
            <div class="col-md-3">
                <h2>Sign In</h2>
                <form action="${pageContext.request.contextPath}/sign/in" method="post">
                    <div class="form-group">
                        <label class="form-control-label" for="username">Login</label>
                        <input class="form-control" name="username" id="username">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="password">Password</label>
                        <input class="form-control" type="password" name="password" id="password">
                    </div>
                    <div class="form-group text-center">
                        <input type="submit" class="btn btn-primary float-right" value="Sign in!">
                        <label class="float-left"><input type="checkbox" name="forgetMeNot">Remember me</label>
                    </div>
                </form>
                <div class="clearfix"></div>
                <hr/>
                <p>Not a debtr yet? <a href="${pageContext.request.contextPath}/sign/up">Click to sign up</a>!</p>
            </div>
        </div>
    </div>
</div>
