
function loadMessages(data) {
	$("#messageList table tr:gt(0)").remove();
	$.each(data, function(index, message) {
		var result = '<tr data-id="' + message.id + '" >';
		result += '<td>' + message.subject + '</td>';
		result += '<td>' + message.sender.email + '</td>';
		result += '<td>' + message.dateSent + '</td></tr>';

		$("#messageList table").append(result);
	});
}

function prepareRows() {
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
			result += '<h3><b>Date sent: </b>' + date + '</h3>';
			result += '<br /><p>' + data.text + '</p>';
			$("#messageDetail").html(result);
		}, 'json');
	});
}

$(document).ready(function() {
	prepareRows();

	$("#inbox").click(function() {
		$.post("/profile/message/inbox", function(data) {
			loadMessages(data);
			prepareRows();
		}, 'json');
	});

	$("#sent").click(function() {
		$.post("/profile/message/sent", function(data) {
			loadMessages(data);
			prepareRows();
		}, 'json');
	});
	
	$("#newMessage").click(function(){
		var children = $("#content").children().hide();
		
		$("#msgDiv").css("display", "block");
	});
	
	$("#messageCancel").click(function(){
		var children = $("#content").children().show();
		
		$("#msgDiv").css("display", "none");
	});
	
	
	$("#receiverEmail").focusout(function() {
		var text = $("#receiverEmail").val();
		
		$.post("/profile/messages/validateEmail", {email:text}, function(data) {
			if (data != text) {
				alert(data);
				$("#receiverEmail").val("");
			}
		});
	});
	
	$("#messageForm").submit(function (event){
		if($("#receiverEmail").val() == ""){
			event.preventDefault();
		}
	});
	
});