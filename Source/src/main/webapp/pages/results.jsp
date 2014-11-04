<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<script>
	$(document).ready(function() {
		$("#city").autocomplete({
			minLength : 2
		});
		$("#city").autocomplete({
			source : <c:import url="getzipcodes.jsp" />
		});
		$("#city").autocomplete("option", {
			enabled : true,
			autoFocus : true
		});
	});
</script>

<h1>Search results:</h1>

<hr />

<c:choose>
	<c:when test="${empty results}">
		<p>No results found!
	</c:when>
	<c:otherwise>
		<div id="resultsDiv">
			<c:forEach var="ad" items="${results}">
				<div>
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
						<p>Move in date: ${ad.moveInDate }</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:otherwise>
</c:choose>

<form:form method="post" modelAttribute="searchForm" action="/results"
	id="filterForm" autocomplete="off">

	<div id="filterDiv">
		<h2>Filter results:</h2>
		<form:checkbox name="room" id="room" path="typeHelper" /><label>Room</label>
		<form:checkbox name="studio" id="studio" path="typeHelper" /><label>Studio</label>
	
		<form:checkbox style="display:none" name="neither" id="neither" path="noRoomNoStudio" />
		<form:checkbox style="display:none" name="both" id="both" path="bothRoomAndStudio" />
		<form:checkbox style="display:none" name="type" id="type" path="studio" />
		<form:checkbox style="display:none" name="filtered" id="filtered" path="filtered" />
		<form:errors path="noRoomNoStudio" cssClass="validationErrorText" /> <br />
	
		<label for="city">City / zip code:</label>
		<form:input type="text" name="city" id="city" path="city"
			placeholder="e.g. Bern" tabindex="3" />
		<form:errors path="city" cssClass="validationErrorText" /><br />
			
		<label for="radius">Within radius of (max.):</label>
		<form:input id="radiusInput" type="number" path="radius"
			placeholder="e.g. 5" step="5" value="5" />
		km
		<form:errors path="radius" cssClass="validationErrorText" />
		<br /> <label for="prize">Price (max.):</label>
		<form:input id="prizeInput" type="number" path="prize"
			placeholder="e.g. 5" step="50" value="500" />
		CHF
		<form:errors path="prize" cssClass="validationErrorText" /><br />
		
		<hr style="height:2px; margin-bottom:10px"/>		
		
		<table style="width: 80%">
			<tr>
				<td><form:checkbox id="field-smoker" path="smokers" value="1" /><label>Animals
						allowed</label></td>
				<td><form:checkbox id="field-animals" path="animals" value="1" /><label>Smoking
						inside allowed</label></td>
			</tr>
			<tr>
				<td><form:checkbox id="field-garden" path="garden" value="1" /><label>Garden
						(co-use)</label></td>
				<td><form:checkbox id="field-balcony" path="balcony" value="1" /><label>Balcony
						or Patio</label></td>
			</tr>
			<tr>
				<td><form:checkbox id="field-cellar" path="cellar" value="1" /><label>Cellar
						or Attic</label></td>
				<td><form:checkbox id="field-furnished" path="furnished"
						value="1" /><label>Furnished</label></td>
			</tr>
			<tr>
				<td><form:checkbox id="field-cable" path="cable" value="1" /><label>Cable
						TV</label></td>
				<td><form:checkbox id="field-garage" path="garage" value="1" /><label>Garage</label>
				</td>
			</tr>
		</table>
			
		<label>Food preference:</label> <form:radiobutton
			path="food" value="Everything" checked="checked" /><label>Everything</label>
		<form:radiobutton path="food" value="Vegetarian" /><label>Vegetarian</label>
		<form:radiobutton path="food" value="Vegan" /><label>Vegan</label> <br>

		
		
		
		
		
		
		
		
		
		
				
		<button type="submit" tabindex="7" onClick="validateType(this.form)">Search</button>	
		<button type="reset" tabindex="8">Cancel</button>
	</div>
</form:form>

<c:import url="template/footer.jsp" />