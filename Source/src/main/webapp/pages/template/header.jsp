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
			<a href="/">LOGO</a>
		</h1>
	</div>
	<div class="right">
		<nav>
			<ol>
				<li><c:choose>
						<c:when test="${loggedIn}">
						${pageContext.request.userPrincipal.name}
						</c:when>
						<c:otherwise>
						Account
						</c:otherwise>
					</c:choose></li>
				<li><a href="/profile/placeAd">Place</a></li>
				<li>Search</li>
				<c:choose>
						<c:when test="${loggedIn}">
						<li><a href="/logout">Logout</a></li>
						</c:when>
						<c:otherwise>
						<li><a href="/login">Login</a></li>
						</c:otherwise>
					</c:choose>
			</ol>
		</nav>
	</div>
</header>

<body>
	<!-- will be closed in footer-->
	<div id="content">