$(document).ready(function(){
	
	$(".pictureThumbnail button").click(function (){
		var adId = $(this).attr("ad-id");
		var pictureId = $(this).attr("picture-id");
		
		$.post("/profile/editAd/deletePictureFromAd", {adId:adId, pictureId:pictureId}, function(){
			$(this).remove();
		});
	});
	
});