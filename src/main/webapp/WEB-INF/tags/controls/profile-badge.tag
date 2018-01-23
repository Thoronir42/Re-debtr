<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="profile" type="cz.zcu.students.kiwi.redebtr.model.UserProfile" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rd" uri="/WEB-INF/functions/helpersFunctions.taglib.tld" %>

<c:set var="pColor" value="${rd:profileColor(profile)}"/>
<a class="profile-badge" style="background: ${pColor}; border-color: ${pColor};"
   data-toggle="tooltip" data-title="${profile.fullName}"
   href="${pageContext.request.contextPath}/user/profile/${profile.locator}">
   <img src="${rd:profileAvatar(profile)}">
</a>
