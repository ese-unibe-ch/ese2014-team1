<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/searchHeader.jsp" />

<h1>Sign up</h1>

<form:form method="post" modelAttribute="signupForm" action="signupForm">
	<fieldset>
		<legend>Enter Your Information</legend>
			<label for="field-username">Username:</label>
			<form:input path="username" id="field-username" />
		
			<label for="field-email">Email</label>
			<form:input path="email" id="field-email" />
		
			<label for="field-firstName">First Name</label>
			<form:input path="firstName" id="field-firstName" />
			
			<label for="field-lastName">First Name</label>
			<form:input path="lastName" id="field-lastName" />
				
			<button type="submit" class="btn btn-primary">Sign up</button>
	</fieldset>
</form:form>

<c:import url="template/searchFooter.jsp" />