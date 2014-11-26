<%@page import="ch.unibe.ese.team1.model.Ad"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="template/header.jsp" />

<pre><a href="/">Home</a>   >   <a href="/profile/placeAd">Place ad</a>   >   Ad Description</pre>

<script src="/js/image_slider.js"></script>
<script src="/js/adDescription.js"></script>

<script>
	$(document).ready(function() {
		var shownAdvertisementID = "${shownAd.id}";
		var shownAdvertisement = "${shownAd}";
		$.post("/bookmark", {id: shownAdvertisementID, screening: true}, function(dataFirst) {
			if(dataFirst == 2) {
				$('#bookmarkButton').replaceWith($('<div class="right" id="bookmarkedButton">' + "Bookmarked" + '</div>'));
			}
		});
		$("#bookmarkButton").click(function() {
			$.post("/bookmark", {id: shownAdvertisementID, screening: false}, function(dataSecond) {
				switch(dataSecond) {
				case 0:
					alert("You need to be logged in to bookmark ads.");
					break;
				case 1:
					alert("ERROR 277489. Please contact the WebAdmin.");
					break;
				// Not necessary since new ID for Bookmarked Tag !!!
				case 2:
					alert("This ad is already bookmarked...");
					break;
				case 3:
					alert("Has been bookmarked!");
					$('#bookmarkButton').replaceWith($('<div class="right" id="bookmarkedButton">' + "Bookmarked" + '</div>'));
					break;
				default:
					alert("ERROR 277411. Please contact the WebAdmin.");
					
				}
			});
		});
	});
</script>

<!-- format the dates -->
<fmt:formatDate value="${shownAd.moveInDate}" var="formattedMoveInDate"
	type="date" pattern="dd.MM.yyyy" />
<fmt:formatDate value="${shownAd.creationDate}" var="formattedCreationDate"
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


<h1>${shownAd.title}<div class="right" id="bookmarkButton">Bookmark Me</div></h1>
<hr />

<section>
	<table id="adDescTable" class="adDescDiv">
		<tr>
			<td><h2>Type</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.studio}">Studio</c:when>
					<c:otherwise>Room</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<td><h2>Address</h2></td>
			<td>
				<a href="http://maps.google.com/?q=${shownAd.street},${shownAd.zipcode},${shownAd.city}">${shownAd.street},
						${shownAd.zipcode} ${shownAd.city}</a>
			</td>
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
			<td><h2>Square Meters</h2></td>
			<td>${shownAd.squareFootage}m²</td>
		</tr>
		<tr>
			<td><h2>Ad created on</h2></td>
			<td>${formattedCreationDate}</td>
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

<hr class="clearBoth" />

<section>
	<div id="descriptionTexts">
		<div class="adDescDiv">
			<h2>Room Description</h2>
			<p>${shownAd.roomDescription}</p>
		</div>
		<br />

		<div class="adDescDiv">
			<h2>Roommates</h2>
			<p>${shownAd.roommates}</p>
			<c:forEach var="mate" items="${shownAd.registeredRoommates}">
				<div class="roommate">
				<table id="mate">
					<tr>
					<!-- Link auf Profil -->
						<td>
						<c:choose>
							<c:when test="${mate.picture.filePath != null}">
								<img src="${mate.picture.filePath}">
							</c:when>
							<c:otherwise>
								<img src="/img/avatar.png">
							</c:otherwise>
						</c:choose>
						</td>
						<td>${mate.firstName} ${mate.lastName}</td>
						<td>${mate.username}</td>
						<td>
						<c:choose>
							<c:when test="${mate.gender == 'MALE'}">
								male
							</c:when>
							<c:otherwise>
								female
							</c:otherwise>
						</c:choose></td>
					<!-- /Link auf Profil -->
					</tr>
				</table>
			</div>
			</c:forEach>
		</div>
		<br />

		<div class="adDescDiv">
			<h2>Preferences</h2>
			<p>${shownAd.preferences}</p>
		</div>
		<br />
		
		<div id="visitList" class="adDescDiv">
			<h2>Visiting times</h2>
			<table>
				<c:forEach items="${visits }" var="visit">
					<tr>
						<td>
							<fmt:formatDate value="${visit.startTimestamp}" pattern="dd-MM-yyyy " />
							&nbsp; from
							<fmt:formatDate value="${visit.startTimestamp}" pattern=" HH:mm " />
							until
							<fmt:formatDate value="${visit.endTimestamp}" pattern=" HH:mm" />
						</td>
						<td>
							<button class="thinButton" type="button" data-id="${visit.id}">Send enquiry to advertiser</button>
							
							
							
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>

	<table id="checkBoxTable" class="adDescDiv">
		<tr>
			<td><h2>Smoking inside allowed</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.smokers}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<td><h2>Animals allowed</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.animals}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<td><h2>Furnished Room</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.furnished}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>
		
		<tr>
			<td><h2>WiFi available</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.internet}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<td><h2>Cable TV</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.cable}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<td><h2>Garage</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.garage}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<td><h2>Cellar</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.cellar}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<td><h2>Balcony</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.balcony}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>

		<tr>
			<td><h2>Garden</h2></td>
			<td>
				<c:choose>
					<c:when test="${shownAd.garden}">yes</c:when>
					<c:otherwise>no</c:otherwise>
				</c:choose>
			</td>
		</tr>

	</table>
</section>

<div class="clearBoth"></div>
<br>

<table id="advertiserTable" class="adDescDiv">
	<tr>
		<td><c:choose>
				<c:when test="${shownAd.user.picture.filePath != null}">
					<img src="${shownAd.user.picture.filePath}">
				</c:when>
				<c:otherwise>
					<img src="/img/avatar.png">
				</c:otherwise>
			</c:choose>
		</td>

		<td><a href=/profile/user?id=${shownAd.user.id}>${shownAd.user.username}</a></td>

		<td>
			<form>
				<button id="newMsg" type="button">Contact Advertiser</button>
			</form>
		</td>
	</tr>
</table>

<c:import url="getMessageForm.jsp" />

<script src="/js/messageForAdDescription.js"></script>

<c:import url="template/footer.jsp" />