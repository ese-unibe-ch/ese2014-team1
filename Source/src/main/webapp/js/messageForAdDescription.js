$(document).ready(function() {
	
	$("#newMsg").click(function(){
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