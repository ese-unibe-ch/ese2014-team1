<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<head>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<Title>Our flat search website!</Title>
</head>

<!-- check if user is logged in -->
<security:authorize var="loggedIn" url="/profile" />

<header>
	<div class="left">
		<h1>
			<a href="/"><img src="/img/red-home-icon.png"></a>
			<a href="/"><img src="img/home-page-icon.png"></a>
		</h1>
	</div>
	<div class="right">
		<nav>
			<ul>
				<li><c:choose>
						<c:when test="${loggedIn}">
						<a href="#">${pageContext.request.userPrincipal.name}</a>
						<ul>
							<li><a href="#">My Rooms</a></li>
							<li><a href="#">Messages (99)</a></li>
							<li><a href="#">Calendar</a></li>
							<li><a href="#">Alerts</a></li>
							<li><a href="#">Public Profile</a></li>
							<li><a href="#">Settings</a></li>
							<li><a href="/logout">Logout</a></li>
						</ul>
						</c:when>
						<c:otherwise>
							<li><a href="/login">Login</a></li>
						</c:otherwise>
					</c:choose></li>
				<li><a href="/profile/placeAd">Place</a></li>
				<li><a href="#">Search</a></li>
			</ul>
		</nav>
	</div>
</header>

<body>
	<!-- will be closed in footer-->
	<div id="content">