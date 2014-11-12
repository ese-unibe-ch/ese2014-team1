<%@page import="ch.unibe.ese.team1.model.Ad"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="template/header.jsp" />
<c:import url="getMessageForm.jsp" />

<script src="/js/messageForAdDescription.js"></script>

<div id="userDiv">
		<c:choose>
			<c:when test="${user.picture.filePath != null}">
				<img src="${user.picture.filePath}">
			</c:when>
			<c:otherwise>
				<img src="/img/avatar.png">
			</c:otherwise>
		</c:choose>
	<p>
		<h2>Username</h2>${user.email}<p>
		<h2>First name</h2>${user.firstName}<p>
		<h2>Last name</h2>${user.lastName}<p>
		<hr class="slim">		
		<h2>About me</h2>${user.aboutMe}
		<hr class="slim">		
		<form>
			<button id="newMsg" type="button">Message</button>
		</form>
</div>
<c:import url="template/footer.jsp"/>