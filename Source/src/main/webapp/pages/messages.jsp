<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<script>
	$(document).ready(function() {
		var rows = $("#messageList table tr:gt(0)");
		$(rows).hover(function() {
			$(this).children().css("background-color", "#ececec");
		}, function() {
			$(this).children().css("background-color", "white");
		});
		/* $(rows).click(function(){
			var id = $(this).attr("data-id");
			$.post("/profile/messages/getMessage?id=")
		}); */
	});
</script>


<h1>Messages</h1>
<hr />
<div id="folders"></div>
<div id="messageList">
	<table class="styledTable">
		<tr>
			<th id="subjectColumn">Subject</th>
			<th>Sender</th>
			<th>Date sent</th>
		</tr>
		<c:forEach items="${messages }" var="message">
			<tr data-id="${message.id}">
				<td>${message.subject }</td>
				<td>${message.sender.email}</td>
				<td>${message.dateSent}</td>
			</tr>
		</c:forEach>
	</table>
	<hr />
	<div id="messageDetail">
		<h2>${messages[0].subject }</h2>
		<h3>
			<b>From: </b> ${messages[0].sender.email }
		</h3>
		<h3>
			<b>Date sent:</b> ${messages[0].dateSent }
		</h3>
		<br />
		<p>${messages[0].text }</p>
	</div>
</div>
<c:import url="template/footer.jsp" />