<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!-- check if user is logged in -->
<security:authorize var="loggedIn" url="/profile" />

<c:import url="template/header.jsp" />

<h1>Login</h1>

<c:choose>
	<c:when test="${loggedIn}">
		<p>You are already logged in!</p>
	</c:when>
	<c:otherwise>
		<c:if test="${!empty param.error}">
			<p>Incorrect login name or password. Please retry using correct
				login name and password.</p>
			<br />
		</c:if>
		<form method="post" action="/j_spring_security_check">
			<label for="field-username">Username:</label> <input
				name="j_username" id="field-username" /> <label
				for="field-password">Password:</label> <input name="j_password"
				id="field-password" type="password" />
			<button type="submit">Login</button>
		</form>
		<br />
		<p>
			<i>Hint: I have a feeling that "johndoe", "password" would be a
				good login.</i>
		</p>
		<p>
			<a href="<c:url value="/signup" />">Sign up as a new user</a>
			instead.
		</p>
	</c:otherwise>
</c:choose>

<c:import url="template/footer.jsp" />