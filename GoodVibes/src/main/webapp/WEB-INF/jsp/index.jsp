<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Home Page</title>
</head>
<body>
	<h2>Home Page</h2>
	<br>
	<br><c:if test="${!empty currentUser}">
		${currentUser.username} is logged in
		</c:if>
	<br>
	<a href="${pageContext.request.contextPath}/account/welcome">user account</a>
	<br>
	<br>
</body>
</html>
