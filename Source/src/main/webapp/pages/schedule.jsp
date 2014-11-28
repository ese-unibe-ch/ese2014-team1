<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h2>Your presentations</h2>

<div id="presentationsDiv">			
<c:choose>
	<c:when test="${empty presentations}">
		<p>You currently haven't scheduled any presentations.
	</c:when>
	<c:otherwise>
		<table class="styledTable" id="visits">
			<thead>
			<tr>
				<th>Address</th>
				<th>From</th>
				<th>Until</th>
				<th>Visit Ad</th>
				<th>Visitors</th>
			</tr>
			</thead>
		<c:forEach var="presentation" items="${presentations}">
			<tr>
				<td>${presentation.ad.street} </td>
				<td>${presentation.startTimestamp}</td>
				<td>${presentation.endTimestamp}</td>
				<td><a href="/ad?id=${presentation.ad.id}"><button>Visit</button></a></td>
				<td><a href="/profile/visitors?visit=${presentation.id}"><button>See List</button></a></td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</div><br />

<h2>Your visits</h2>

<div id="visitsDiv">			
<c:choose>
	<c:when test="${empty visits}">
		<p>You currently haven't scheduled any visits.
	</c:when>
	<c:otherwise>
		<table class="styledTable" id="visits">
			<thead>
			<tr>
				<th>Address</th>
				<th>From</th>
				<th>Until</th>
				<th>Visit Ad</th>
			</tr>
			</thead>
		<c:forEach var="visit" items="${visits}">
			<tr>
				<td>${visit.ad.street} </td>
				<td>${visit.startTimestamp}</td>
				<td>${visit.endTimestamp}</td>
				<td><a href="/ad?id=${visit.ad.id}"><button>Visit</button></a></td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</div>

<c:import url="template/footer.jsp" />