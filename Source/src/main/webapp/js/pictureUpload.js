/**
 * 
 */
$(function() {
	$('#field-pictures').fileupload({
		url: '/profile/placeAd/uploadPictures',
		dataType : 'json',
		
		done : function(e, data) {
			$.each(data.files, function(index, file) {
				$("#uploaded-pictures").text($("#uploaded-pictures").text() + ", " + file.name);
			});
		}
	});
});


