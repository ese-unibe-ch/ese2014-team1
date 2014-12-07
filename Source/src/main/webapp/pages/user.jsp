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

<pre><a href="/">Home</a>   &gt;   Profile</pre>

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
	<h2>Name</h2>${user.firstName}
	${user.lastName}
	<p>
	<hr class="slim">
	<h2>About me</h2>${user.aboutMe}
	<hr class="slim">
	<form>
		<c:choose>
			<c:when test="${loggedIn}">
			
			
			
			<button id="newMsg" type="button">Message</button>
			<c:choose>
				<c:when test="${principalID eq user.id}">
					<a class="button" href="/profile/editProfile">Edit Profile</a>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
			</c:when>
			<c:otherwise>
				<p>Please log in to contact this person.</p>
			</c:otherwise>
		</c:choose>
	</form>
</div>
<c:import url="template/footer.jsp" />