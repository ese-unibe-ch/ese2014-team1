<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

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
		$("#addbutton").click(function(){
			var text = $("#otherroommates").val();
			var textS = $("#room_friends").val();
			//$(myFriendsFirst).html($("#otherroommates").val());
			//text += myFriendsFirst.val();
			//var myFriendsSecond = html($("#room_friends").val()); // Breaks the code
			//myFriendsFirst.value += myFriendsSecond.value + " ";
			$("#otherroommates").html(text + textS);
		//	$("#otherroommates").html($("#room_friends").val()); CORRECT
		//});
		//$("#addbutton").click(function(){
		//	$("#otherroommates").get(0).value += "supernice"; //html($("#room_friends").val());
		//	
		//});
		//("#addbutton").click(function(){
		//	$("#otherroommates").val(function(html("#room_friends", "#otherroommates")) {return "#otherroommates" + "#room_friends"}
			
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
				<td><form:radiobutton id="type-room" path="type" value="Room"
						checked="checked" />Room <form:radiobutton id="type-studio"
						path="type" value="Studio" />Studio</td>
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
				<td><form:checkbox id="field-smoker" path="smoker" value="1" />
				</td>
				<td><form:checkbox id="field-animals" path="animals" value="1" />
				</td>
			</tr>

		</table>
	</fieldset>


	<br />
	<fieldset>
		<legend>Room Description</legend>
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
				<td><form:input type="text" id="room_friends" path="room_friends"
						placeholder="email"/> <!-- tabindex="x" -->
			
						<div id="addbutton">+</div>
						<p id="otherroommates" value="tst">tst</p>
				</td>
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

		<label for="field-pictures">Pictures</label>
		<form:input type="file" path="pictures" id="field-pictures"
			accept="image/*" multiple="multiple" />
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
