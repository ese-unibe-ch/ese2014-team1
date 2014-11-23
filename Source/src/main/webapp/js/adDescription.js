$(document).ready(function() {
	var buttons = $("#visitList table tr button");
	
	$(buttons).click(function() {
		var buttonText = $(this).attr("class");
		
		if (buttonText == 'thinInactiveButton') {
			return;
		}
		
		var id = $(this).attr("data-id");
		
		$.get("/profile/enquiries/sendEnquiryForVisit?id=" + id);
			
		$(this).addClass('thinInactiveButton').removeClass('thinButton');
		$(this).html('Enquiry sent');
	});
	
});
