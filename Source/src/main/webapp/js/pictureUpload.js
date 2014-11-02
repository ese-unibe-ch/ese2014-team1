/**
 * 
 */
$(function() {
	$('#field-pictures').fileupload({
		url: '/profile/placeAd/uploadPictures',
		dataType : 'json',
		
		done : function(e, data) {
			$.each(data.files, function(index, file) {
				var size = (file.size/1024).toFixed(2) + ' KB';
				$("#uploaded-pictures").append('<tr><td>'+ file.name + '</td><td>' + size + '</td></tr>');
			});
		}
	});
});


