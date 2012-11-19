<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>
	<title>Create Account</title>
	<style>
		.errorblock {
			color: #ff0000;
			background-color: #ffEEEE;
			border: 2px solid #ff0000;
			padding: 8px;
			margin: 16px;
		}
		.error {
			color: #ff0000;
			background-color: #ffEEEE;
		}	
	</style>
</head>

<body>
	<br>
	<br>
	<br>
	<br>
	<form:form method="post" modelAttribute="user" >
		<%-- useful for debugging (displays all errors) <form:errors path="*" cssClass="errorblock"/> --%>
		<table>
			<tr>
		      <td>Username</td>
      		  <td><form:input path="username"/></td> 
      		  <td><form:errors path="username" cssClass="error"/></td> 
			</tr>
			<tr>
		      <td>Email</td>
      		  <td><form:input path="email"/></td> 
      		  <td><form:errors path="email" cssClass="error"/></td> 
			</tr>
			<tr>
		      <td>Password</td>
      		  <td><form:input path="password"/></td> 
      		  <td><form:errors path="password" cssClass="error"/></td> 
			</tr>
 			<tr>
		      <td>Password Confirmation</td>
      		  <td><form:input path="passwordConfirmation"/></td> 
      		  <td><form:errors path="passwordConfirmation" cssClass="error"/></td> 
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Create Account!" /></td>
			</tr>
		</table>
	</form:form>
	
	<br>
	<br>
	<a href="${pageContext.request.contextPath}">home</a>
 
</body>
</html>