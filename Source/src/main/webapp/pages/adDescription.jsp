<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="template/header.jsp" />

<%-- call via:
http://localhost:8080/profile/ad.?adId=1 
--%>

<h1>${shownAd.title}</h1>
<hr />

<table style="width:50%">
  <tr>
    <td><h2>Address</h2></td>
    <td>${shownAd.street}, ${shownAd.zipcode} ${shownAd.city}</td>
  </tr>
  
  <tr>
    <td><h2>Available from</h2></td>
    <td>${shownAd.moveInDate}</td>
  </tr>
  
  <tr>
    <td><h2>Move-out Date</h2></td>
    <td>${shownAd.moveOutDate}</td>
  </tr>
  
  <tr>
    <td><h2>Prize</h2></td>
    <td>${shownAd.prizePerMonth}</td>
  </tr>
  
  <tr>
    <td><h2>Square Footage</h2></td>
    <td>${shownAd.squareFootage}</td>
  </tr>
</table> 





<!-- Needed for image slider -->
<script src="/js/jssor.js"></script>
<script src="/js/jssor.slider.js"></script>

<script>
	jQuery(document).ready(function ($) {
		var options = {
        	$DragOrientation: 3,             //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)
            $ArrowNavigatorOptions: {        //[Optional] Options to specify and enable arrow navigator or not
            $Class: $JssorArrowNavigator$,   //[Requried] Class to create arrow navigator instance
            $ChanceToShow: 2,                //[Required] 0 Never, 1 Mouse Over, 2 Always
            $AutoCenter: 0,                  //[Optional] Auto center arrows in parent container, 0 No, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
            $Steps: 1                        //[Optional] Steps to go for each navigation request, default value is 1
        }
    };
    var jssor_slider1 = new $JssorSlider$("slider1_container", options);
    });
</script>
    
    
 <!-- Slider Begin -->
    <div id="slider1_container" style="position: relative; top: 0px; left: 0px; width: 600px;
        height: 300px; ">

        <!-- Slides Container -->
        <div u="slides" style="cursor: move; position: absolute; left: 0px; top: 0px; width: 600px; height: 300px;
            overflow: hidden;">
           
            <div><img u="image" src="http://i59.tinypic.com/2552f11.jpg" /></div>
       	    <div><img u="image" src="http://i61.tinypic.com/2wg6paf.jpg" /></div>
        	<div><img u="image" src="http://i57.tinypic.com/2h3nf5x.jpg" /></div>
        	<div><img u="image" src="/img/image_slider/1.jpg" /></div>
       	    <div><img u="image" src="/img/image_slider/2.jpg" /></div>
        	<div><img u="image" src="/img/image_slider/3.jpg" /></div>
        </div>
        
        <!-- Arrow Navigator Skin Begin -->
        <style>
            .jssora03l, .jssora03r, .jssora03ldn, .jssora03rdn
            {
            	position: absolute;
            	cursor: pointer;
            	display: block;
                background: url(/img/image_slider/arrows.png) no-repeat;
                overflow:hidden;
            }
            .jssora03l { background-position: -3px -33px; }
            .jssora03r { background-position: -63px -33px; }
            .jssora03l:hover { background-position: -123px -33px; }
            .jssora03r:hover { background-position: -183px -33px; }
            .jssora03ldn { background-position: -243px -33px; }
            .jssora03rdn { background-position: -303px -33px; }
        </style>
        <!-- Arrow Left -->
        <span u="arrowleft" class="jssora03l" style="width: 55px; height: 55px; top: 123px; left: 8px;">
        </span>
        <!-- Arrow Right -->
        <span u="arrowright" class="jssora03r" style="width: 55px; height: 55px; top: 123px; right: 8px">
        </span>
        <!-- Arrow Navigator Skin End -->
        <a style="display: none" href="http://www.jssor.com">sliders</a>
    </div>
<!-- Slider End -->

    





<!-- 
	private long id;
	private String title;
	private int zipcode;
	private Date moveInDate;
	private Date moveOutDate;
	private int prizePerMonth;
	private int squareFootage;
	private String roomDescription;
	private String preferences;
	private String roommates;
	private boolean smoker;
	private boolean animals;
	private Set<AdPicture> pictures;
	private User user;
 -->

<c:import url="template/footer.jsp" />
