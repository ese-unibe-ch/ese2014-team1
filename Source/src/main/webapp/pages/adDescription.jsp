<%@page import="ch.unibe.ese.team1.model.Ad"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="template/header.jsp" />

<!-- format the dates -->
<fmt:formatDate value="${shownAd.moveInDate}" var="formattedMoveInDate"
	type="date" pattern="dd.MM.yyyy" />
<c:choose>
	<c:when test="${empty shownAd.moveOutDate }">
		<c:set var="formattedMoveOutDate" value="unlimited" />
	</c:when>
	<c:otherwise>
		<fmt:formatDate value="${shownAd.moveOutDate}"
			var="formattedMoveOutDate" type="date" pattern="dd.MM.yyyy" />
	</c:otherwise>
</c:choose>


<h1>${shownAd.title}</h1>
<hr />

<section style="width: 100%">
	<table id="adDescTable">
		<tr>
			<td><h2>Type</h2></td>
			<td><c:choose>
					<c:when test="${shownAd.studio}">
					Studio
			</c:when>
					<c:otherwise>
					Rooom
				</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td><h2>Address</h2></td>
			<td>${shownAd.street},${shownAd.zipcode}${shownAd.city}</td>
		</tr>

		<tr>
			<td><h2>Available from</h2></td>
			<td>${formattedMoveInDate}</td>
		</tr>

		<tr>
			<td><h2>Move-out Date</h2></td>
			<td>${formattedMoveOutDate}</td>
		</tr>

		<tr>
			<td><h2>Monthly Rent</h2></td>
			<td>${shownAd.prizePerMonth}CHF</td>
		</tr>

		<tr>
			<td><h2>Square Footage</h2></td>
			<td>${shownAd.squareFootage}m²</td>
		</tr>

	</table>


	<!-- Needed for image slider -->
	<script src="/js/jssor.js"></script>
	<script src="/js/jssor.slider.js"></script>
	<script>
		jQuery(document).ready(
				function($) {
					var options = {
						$DragOrientation : 3, //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)
						$ArrowNavigatorOptions : { //[Optional] Options to specify and enable arrow navigator or not
							$Class : $JssorArrowNavigator$, //[Requried] Class to create arrow navigator instance
							$ChanceToShow : 2, //[Required] 0 Never, 1 Mouse Over, 2 Always
							$AutoCenter : 0, //[Optional] Auto center arrows in parent container, 0 No, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
							$Steps : 1
						//[Optional] Steps to go for each navigation request, default value is 1
						}
					};
					var jssor_slider1 = new $JssorSlider$("slider1_container",
							options);
				});
	</script>

	<!-- Slider Begin -->
	<div id="slider1_container"
		style="position: relative; top: 0px; left: 50%; width: 600px; height: 400px;">

		<!-- Slides Container -->
		<div u="slides"
			style="cursor: move; position: absolute; left: 0px; top: 0px; width: 600px; height: 400px; overflow: hidden;">

			<c:forEach items="${shownAd.pictures}" var="picture">
				<div>
					<img u="image" src="${picture.filePath}" />
				</div>
			</c:forEach>

		</div>

		<!-- Arrow Navigator Skin Begin -->
		<style>
.jssora03l, .jssora03r, .jssora03ldn, .jssora03rdn {
	position: absolute;
	cursor: pointer;
	display: block;
	background: url(/img/image_slider/arrows.png) no-repeat;
	overflow: hidden;
}

.jssora03l {
	background-position: -3px -33px;
}

.jssora03r {
	background-position: -63px -33px;
}

.jssora03l:hover {
	background-position: -123px -33px;
}

.jssora03r:hover {
	background-position: -183px -33px;
}

.jssora03ldn {
	background-position: -243px -33px;
}

.jssora03rdn {
	background-position: -303px -33px;
}
</style>

		<!-- Arrow Left -->
		<span u="arrowleft" class="jssora03l"
			style="width: 55px; height: 55px; top: 123px; left: 8px;"> </span>
		<!-- Arrow Right -->
		<span u="arrowright" class="jssora03r"
			style="width: 55px; height: 55px; top: 123px; right: 8px"> </span>
		<!-- Arrow Navigator Skin End -->
		<a style="display: none" href="http://www.jssor.com">sliders</a>
	</div>
	<!-- Slider End -->

</section>

<!-- clears the float: left and puts the hr under the table without overlapping -->
<div style="clear: both;"></div>
<hr />

<section style="width: 100%">
	<div style="width: 55%; float: left">
		<div style="width: 80%">
			<h2>Room Description</h2>
			<p>${shownAd.roomDescription}</p>
		</div>
		<br />

		<div style="width: 80%">
			<h2>Roommates</h2>
			<p>${shownAd.roommates}</p>
		</div>
		<br />

		<div style="width: 80%">
			<h2>Preferences</h2>
			<p>${shownAd.preferences}</p>
		</div>
	</div>


	<table class="advertiserTable">
		<tr>
			<td>
				<h2>Smoking inside allowed</h2>
			</td>
			<td><c:choose>
					<c:when test="${shownAd.smokers}">
        			yes
        		</c:when>
					<c:otherwise>
        			no
        		</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td>
				<h2>Animals allowed</h2>
			</td>
			<td><c:choose>
					<c:when test="${shownAd.animals}">
        			yes
        		</c:when>
					<c:otherwise>
        			no
        		</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td>
				<h2>Furnished Room</h2>
			</td>
			<td><c:choose>
					<c:when test="${shownAd.furnished}">
        			yes
        		</c:when>
					<c:otherwise>
        			no
        		</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td>
				<h2>Cable TV</h2>
			</td>
			<td><c:choose>
					<c:when test="${shownAd.cable}">
        			yes
        		</c:when>
					<c:otherwise>
        			no
        		</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td>
				<h2>Garage</h2>
			</td>
			<td><c:choose>
					<c:when test="${shownAd.garage}">
        			yes
        		</c:when>
					<c:otherwise>
        			no
        		</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td>
				<h2>Food preference</h2> <!-- simply show the shownAd.food String here. -->
			</td>
			<td>${shownAd.food }</td>
		</tr>

		<tr>
			<td>
				<h2>Cellar</h2>
			</td>
			<td><c:choose>
					<c:when test="${shownAd.cellar}">
        			yes
        		</c:when>
					<c:otherwise>
        			no
        		</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td>
				<h2>Balcony</h2>
			</td>
			<td><c:choose>
					<c:when test="${shownAd.balcony}">
        			yes
        		</c:when>
					<c:otherwise>
        			no
        		</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td>
				<h2>Garden</h2>
			</td>
			<td><c:choose>
					<c:when test="${shownAd.garden}">
        			yes
        		</c:when>
					<c:otherwise>
        			no
        		</c:otherwise>
				</c:choose></td>
		</tr>

	</table>
</section>

<div style="clear: both"></div>
<br>

<table style="width: 40%">
	<tr>
		<td><c:choose>
				<c:when test="${shownAd.user.picture.filePath != null}">
					<img src="${shownAd.user.picture.filePath}" width="50" height="50">
				</c:when>
				<c:otherwise>
					<img src="/img/avatar.png" width="50" height="50">
				</c:otherwise>
			</c:choose></td>

		<td>
			<h2 style="margin: 0px">Advertiser</h2>
		</td>

		<td>${shownAd.user.username}</td>
	</tr>
</table>

<br>
<br>

<c:import url="template/footer.jsp" />
