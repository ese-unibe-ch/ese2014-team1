<%@page import="ch.unibe.ese.team1.model.Ad"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="template/header.jsp" />

<p><span class="breadcrumbs"><pre><a href="/"> Home </a>   >   <a href="/profile/placeAd">Place ad</a>   >   Ad Description</pre></span></p>

<script src="/js/image_slider.js"></script>

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
					Room
				</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<td><h2>Address</h2></td>
			<td>${shownAd.street}, ${shownAd.zipcode} ${shownAd.city}</td>
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
			<td>${shownAd.prizePerMonth} CHF</td>
		</tr>

		<tr>
			<td><h2>Square Footage</h2></td>
			<td>${shownAd.squareFootage} m²</td>
		</tr>

	</table>

</section>

<div id="image-slider">
	<div id="left-arrow">
		<img src="/img/left-arrow.png" />
	</div>
	<div id="images">
		<c:forEach items="${shownAd.pictures}" var="picture">
			<img src="${picture.filePath}" />
		</c:forEach>
	</div>
	<div id="right-arrow">
		<img src="/img/right-arrow.png" />
	</div>
</div>

<hr style="clear: both" />

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