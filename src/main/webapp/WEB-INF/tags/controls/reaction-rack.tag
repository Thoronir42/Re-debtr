<%@ tag description="User thumb" language="java" %>

<jsp:useBean id="ReactionManager" class="cz.zcu.students.kiwi.redebtr.persistence.ReactionManager"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="post-reactions">
    <c:forEach items="${ReactionManager.standardReactions}" var="reaction">
        <a class="btn btn-sm btn-outline-light" data-title="${reaction.caption}" data-toggle="tooltip">
            <i class="fa fa-${reaction.cssClass}"></i>
        </a>
    </c:forEach>

</div>

