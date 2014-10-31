<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<script src="/js/jquery.ui.widget.js"></script>
<script src="/js/jquery.iframe-transport.js"></script>
<script src="/js/jquery.fileupload.js"></script>

<script src="/js/pictureUpload.js"></script>


<script>
	$(document).ready(function() {
		$("#field-city").autocomplete({
			minLength : 2
		});
		$("#field-city").autocomplete({
			source : <c:import url="getzipcodes.jsp" />
		});
		$("#field-city").autocomplete("option", {
			enabled : true,
			autoFocus : true
		});
		$("#field-moveInDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});
		$("#field-moveOutDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});
		$("#addbutton").click(function() {
			var text = $("#room_friends").val();
			$.post("/profile/placeAd/validateEmail", { email: text});
			$("#matesinwg").append(text + "  ; \u00A0\u00A0  ");
			$("#room_friends").val("");
			// splitting is done by
			// ->  "1,2,3".split( "," ) -> [ "1", "2", "3" ]
			// check this page again -> http://api.jquery.com/Types/#htmlString
		});		
	});
</script>

<h1>Place an ad</h1>
<hr />

<form:form method="post" modelAttribute="placeAdForm"
	action="/profile/placeAd" id="placeAdForm" autocomplete="off"
	enctype="multipart/form-data">

	<fieldset>
		<legend>General info</legend>
		<table style="width: 80%">
			<tr>
				<td><label for="field-title">Ad Title</label></td>
				<td><label for="type-room">Type:</label></td>
			</tr>

			<tr>
				<td><form:input id="field-title" path="title"
						placeholder="Ad Title" /></td>
				<td><form:radiobutton id="type-room" path="studio" value="0"
						checked="checked" />Room <form:radiobutton id="type-studio"
						path="studio" value="1" />Studio</td>
			</tr>

			<tr>
				<td><label for="field-street">Street</label></td>
				<td><label for="field-city">City / Zip code</label></td>
			</tr>

			<tr>
				<td><form:input id="field-street" path="street"
						placeholder="Street" /></td>
				<td><form:input id="field-city" path="city" placeholder="City" />
					<form:errors path="city" cssClass="validationErrorText" /></td>
			</tr>

			<tr>
				<td><label for="moveInDate">Move-in date</label></td>
				<td><label for="moveOutDate">Move-out date (optional)</label></td>
			</tr>
			<tr>
				<td><form:input type="text" id="field-moveInDate"
						path="moveInDate" /></td>
				<td><form:input type="text" id="field-moveOutDate"
						path="moveOutDate" /></td>
			</tr>

			<tr>
				<td><label for="field-Prize">Prize per month</label></td>
				<td><label for="field-SquareFootage">Square Meters</label></td>
			</tr>
			<tr>
				<td><form:input id="field-Prize" type="number" path="prize"
						placeholder="Prize per month" step="50" /> <form:errors
						path="prize" cssClass="validationErrorText" /></td>
				<td><form:input id="field-SquareFootage" type="number"
						path="squareFootage" placeholder="Prize per month" step="5" /> <form:errors
						path="squareFootage" cssClass="validationErrorText" /></td>
			</tr>

			<tr>
				<td><label for="field-smoker">Smoking inside allowed</label></td>
				<td><label for="field-animals">Animals allowed</label></td>
			</tr>
			<tr>
				<td><form:checkbox id="field-smoker" path="smokers" value="1" />
				</td>
				<td><form:checkbox id="field-animals" path="animals" value="1" />
				</td>
			</tr>

		</table>
	</fieldset>
	
	
	
	


	<br />
	<fieldset>
		<legend>Room Description</legend>
		
		<table style="width: 80%">
			<tr>
				<td>
					<form:checkbox id="field-smoker" path="smokers" value="1" /><label>Animals allowed</label>
				</td>
				<td>
					<form:checkbox id="field-animals" path="animals" value="1" /><label>Smoking inside allowed</label>
				</td>
			</tr>
			<tr>
				<td>
					<form:checkbox id="field-garden" path="garden" value="1" /><label>Garden (co-use)</label>
				</td>
				<td>
					<form:checkbox id="field-balcony" path="balcony" value="1" /><label>Balcony or Patio</label>
				</td>
			</tr>
			<tr>
				<td>
					<form:checkbox id="field-cellar" path="cellar" value="1" /><label>Cellar or Attic</label>
				</td>
				<td>
					<form:checkbox id="field-furnished" path="furnished" value="1" /><label>Furnished</label>
				</td>
			</tr>
			<tr>
				<td>
					<form:checkbox id="field-cable" path="cable" value="1" /><label>Cable TV</label>
				</td>
				<td>
					<form:checkbox id="field-garage" path="garage" value="1" /><label>Garage</label>
				</td>
			</tr>

		</table>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<form:textarea path="roomDescription" rows="10" cols="100"
			placeholder="Room Description" />
		<form:errors path="roomDescription" cssClass="validationErrorText" />
	</fieldset>

	<br />
	<fieldset>
		<legend>Roommates</legend>
		<p>If your roommates have an account, simply add them by email.</p>

		<table style="width: 80%">
			<tr>
				<td><label for="room_friends">Add by email</label></td>
			</tr>

			<tr>
				<td><form:input type="text" id="room_friends"
						path="room_friends" placeholder="email" /> <!-- tabindex="x" -->

					<div id="addbutton">+</div>
					<p id="matesinwg"></p></td>
			</tr>
		</table>


		<form:textarea path="roommates" rows="10" cols="100"
			placeholder="Roommates" />
		<form:errors path="roommates" cssClass="validationErrorText" />
	</fieldset>

	<br />
	<fieldset>
		<legend>Preferences (optional)</legend>
		<form:textarea path="preferences" rows="5" cols="100"
			placeholder="Preferences"></form:textarea>
	</fieldset>

	<fieldset>
		<legend>Pictures (optional)</legend>

		<label for="field-pictures">Pictures</label> <input type="file"
			id="field-pictures" accept="image/*" multiple="multiple" data-url="/profile/placeAd/uploadPictures" />
		<form:errors path="pictures" cssClass="validationErrorText" />

		<br>
	</fieldset>

	<br />
	<div>
		<button type="submit">Place Ad</button>
		<button type="reset">Cancel</button>
	</div>

</form:form>

<c:import url="template/footer.jsp" />
