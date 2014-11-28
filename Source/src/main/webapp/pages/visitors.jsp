<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h2>Visitors for your property:</h2>

<div id="visitorsDiv">			
<c:choose>
	<c:when test="${empty visitors}">
		<p>This property doesn't have any scheduled visitors at the moment.
	</c:when>
	<c:otherwise>
		<table class="styledTable" id="visitors">
			<thead>
			<tr>
				<th>Name</th>
				<th>Username</th>
				<th>Profile</th>
			</tr>
			</thead>
		<c:forEach var="visitor" items="${visitors}">
			<tr>
				<td>${visitor.firstName} ${visitor.lastName }</td>
				<td>${visitor.username}</td>
				<td><a href="/profile/user?id=${visitor.id}"><button>Visit</button></a></td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</div>

<c:import url="template/footer.jsp" />