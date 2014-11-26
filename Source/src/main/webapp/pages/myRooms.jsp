<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<c:import url="template/header.jsp" />

<script>
	$(document).ready(function() {
		
		
	});

</script>


<pre><a href="/">Home</a>   >   My Rooms</pre>

<h1>My Advertisements</h1>
<hr />



<h1>My Bookmarks</h1>
<hr />

<c:choose>
	<c:when test="${empty bookmarkedAds}">
		<p>No results found!
	</c:when>
	<c:otherwise>
	
		<div id="resultsDiv" class="resultsDiv">			
			<c:forEach var="ad" items="${bookmarkedAdvertisements}">
				<div class="resultAd" data-price="${ad.prizePerMonth}" 
								data-moveIn="${ad.moveInDate}" data-age="${ad.moveInDate}">
					<div class="resultLeft">
						<a href="<c:url value='/ad?id=${ad.id}' />"><img
							src="${ad.pictures[0].filePath}" /></a>
						<h2>
							<a href="<c:url value='/ad?id=${ad.id}' />">${ad.title }</a>
						</h2>
						<p>${ad.street}, ${ad.zipcode} ${ad.city}</p>
						<br />
						<p>
							<i><c:choose>
									<c:when test="${ad.studio}">Studio</c:when>
									<c:otherwise>Room</c:otherwise>
								</c:choose></i>
						</p>
					</div>
					<div class="resultRight">
						<h2>CHF ${ad.prizePerMonth }</h2>
						<br /> <br />
						<p>Move-in date: ${ad.moveInDate }</p>
					</div>
				</div>
			</c:forEach>
		</div>
		
	</c:otherwise>
</c:choose>


<c:import url="template/footer.jsp" />