<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h1>Login</h1>

<form method="post" action="/j_spring_security_check">
		<label for="field-username">Username:</label>
		<input name="j_username" id="field-username" />
		<label for="field-password">Password:</label>
		<input name="j_password" id="field-password" />
		<button type="submit">Login</button>
</form>

<c:import url="template/footer.jsp" />