<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
	<title>Login Page</title>
	<style>
	.errorblock {
		color: #ff0000;
		background-color: #ffEEEE;
		border: 2px solid #ff0000;
		padding: 8px;
		margin: 16px;
	}
	</style>
</head>

<body>
	<br>
	<br>
	
	<c:if test="${not empty param.error}">
	  <div class="errorblock">
		LOGIN ERROR<br/>
		Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}<br/>
	  </div>
	</c:if>
	
	<c:if test="${not empty param.loggedout}">
		You have been logged out successfully<br/>
	</c:if>
	<br>
	<br>
	
	<form method="POST" action="<c:url value="/j_spring_security_check"/>">
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="j_username" /> </td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="j_password" /> </td>
			</tr>
			<tr>
				<td>Remember me</td>
				<td><input type="checkbox" name="_spring_security_remember_me" /> </td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Login" />
				</td>
			</tr>
		</table>
	</form>
	<a href="${pageContext.request.contextPath}/login/create-account">Register with us</a>
	
	<br>
	<br>
	<a href="${pageContext.request.contextPath}">home</a>
 
</body>
</html>