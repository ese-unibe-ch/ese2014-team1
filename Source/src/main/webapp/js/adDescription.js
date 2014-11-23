/**
 * 
 */

$(document).ready(function() {
	var buttons = $("#visitList table tr button");
	
	$(buttons).click(function() {
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
