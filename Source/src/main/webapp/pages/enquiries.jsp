<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="template/header.jsp" />
<pre>
	<a href="/">Home</a>   >   Enquiries</pre>

<!-- format the dates -->
<fmt:formatDate value="${enquiries[0].dateSent}" var="formattedDateSent"
	type="date" pattern="HH:mm, dd.MM.yyyy" />

<script>
	$(document).ready(function() {
		var rows = $("#enquiryList table tr:gt(0)");
		$(rows).hover(function() {
			$(this).children().css("background-color", "#ececec");
		}, function() {
			$(this).children().css("background-color", "white");
		});
	});
</script>

<h1>Enquiries</h1>
<hr />
<div id="enquiryList">
	<table class="styledTable">
		<tr>
			<th>Sender</th>
			<th>Ad</th>
			<th>Date sent</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${enquiries}" var="enquiry">
			<fmt:formatDate value="${enquiry.dateSent}"
				var="singleFormattedDateSent" type="date"
				pattern="HH:mm, dd.MM.yyyy" />

			<tr data-id="${enquiry.id}">
				<td>${enquiry.sender.email}</td>
				<td><a href="/ad?id=${enquiry.visit.ad.id }">${enquiry.visit.ad.title }</a></td>
				<td>${singleFormattedDateSent}</td>
				<td>
					<button class="acceptButton">Accept</button>
					<button class="declineButton">Decline</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<c:import url="template/footer.jsp" />