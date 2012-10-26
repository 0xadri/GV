<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>USER Account Welcome Page</title>
</head>
<body>
 
<h2>USER Welcome Page</h2>

	<br>
	<br>${currentUser.username}
	<br>${currentUser.email}
	<br>${currentUser.firstName} ${currentUser.lastName}
	<br>${currentUser.gender}
	<br>${currentUser.location}
	<br><fmt:formatDate pattern="yyyy/MM/dd" value="${currentUser.dob}" />
	<br>${currentUser.websites}
	<br>${currentUser.interests}
	<br>${currentUser.language}
	<br>
	<br>Roles:
		<c:forEach var="role" items="${currentUser.roles}">
	     ${role.roleName}
        </c:forEach>
	<br>
	<br>Roles: 
		<c:forEach var="image" items="${currentUser.images}">
	     ${image.type}, ${image.name}, ${image.contentType}, ${image.length}, ${image.content}<br>
        </c:forEach>
	<br>
	<br>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/<c:url value="j_spring_security_logout" />">logout</a> ¦ <a href="${pageContext.request.contextPath}">home</a>

</body>
</html>