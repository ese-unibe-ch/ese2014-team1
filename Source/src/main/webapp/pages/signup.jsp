<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/LLheader.jsp" />

<h1>Sign up</h1>

<c:choose>
	<c:when test="${empty message}">
		<form:form method="post" modelAttribute="signupForm"
			action="signup">
			<fieldset>
				<legend>Enter Your Information</legend>
				<label for="field-username">Username:</label>
				<form:input path="username" id="field-username" />
				<form:errors path="username" />
								
				<label for="field-password">Password:</label>
				<form:input path="password" id="field-password" type ="password"/>
				<form:errors path="password" />

				<label for="field-email">Email:</label>
				<form:input path="email" id="field-email" />
				<form:errors path="email" />

				<label for="field-firstName">First Name</label>
				<form:input path="firstName" id="field-firstName" />
				<form:errors path="firstName" />

				<label for="field-lastName">Last Name</label>
				<form:input path="lastName" id="field-lastName" />
				<form:errors path="lastName" />
				

				<button type="submit">Sign up</button>
			</fieldset>
		</form:form>
	</c:when>
	<c:otherwise>
		${message}
	</c:otherwise>
</c:choose>
<c:if test="{!empty message}">${message}</c:if>



<c:import url="template/LLfooter.jsp" />