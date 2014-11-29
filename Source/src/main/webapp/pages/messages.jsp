<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="template/header.jsp" />
<pre><a href="/">Home</a>   >   Messages</pre>

<!-- format the dates -->
<fmt:formatDate value="${messages[0].dateSent}" var="formattedDateSent"
	type="date" pattern="HH:mm, dd.MM.yyyy" />

<script>
	$(document).ready(function() {
		var rows = $("#messageList table tr:gt(0)");
		$(rows).hover(function() {
			$(this).children().css("background-color", "#ececec");
		}, function() {
			$(this).children().css("background-color", "white");
		});
		$(rows).click(function() {
			var id = $(this).attr("data-id");
			$.get("/profile/messages/getMessage?id=" + id, function(data) {
				var result = '<h2>' + data.subject + '</h2>';
				result += '<h3><b>From: </b>' + data.sender.email + '</h3>';
				var date = new Date(data.dateSent);
				result += '<h3><b>Date sent: </b>' + data.dateSent + '</h3>';
				result += '<br /><p>' + data.text + '</p>';
				$("#messageDetail").html(result);
			}, 'json');
		});
	});
</script>
<script src="/js/messages.js"></script>

<h1>Messages</h1>
<hr />
<div id="folders">
	<h2 id="inbox">Inbox</h2>
	<h2 id="newMessage">New</h2>
	<h2 id="sent">Sent</h2>
</div>
<div id="messageList">
	<table class="styledTable">
		<tr>
			<th id="subjectColumn">Subject</th>
			<th>Sender</th>
			<th>Date sent</th>
		</tr>
		<c:forEach items="${messages }" var="message">
			<fmt:formatDate value="${message.dateSent}"
				var="singleFormattedDateSent" type="date"
				pattern="HH:mm, dd.MM.yyyy" />

			<tr data-id="${message.id}">
				<td>${message.subject }</td>
				<td>${message.sender.email}</td>
				<td>${singleFormattedDateSent}</td>
			</tr>
		</c:forEach>
	</table>
	<hr />
	<div id="messageDetail" class="divWithLineBreaks">
		<h2>${messages[0].subject }</h2>
		<h3>
			<b>From: </b> ${messages[0].sender.email }
		</h3>
		<h3>
			<b>Date sent:</b> ${formattedDateSent}
		</h3>
		<br />
		<p>${messages[0].text }</p>
	</div>
</div>

<c:import url="getMessageForm.jsp" />


<c:import url="template/footer.jsp" />