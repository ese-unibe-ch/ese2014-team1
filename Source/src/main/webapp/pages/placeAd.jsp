<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<script>
		$(document).ready(function(){
			$("#field-city").autocomplete({minLength: 2});
			$("#field-city").autocomplete({source: <c:import url="getzipcodes.jsp" />});
			$("#field-city").autocomplete("enable");
			$("#field-moveInDate").datepicker({ dateFormat: 'dd-mm-yy' });
			$("#field-moveOutDate").datepicker({ dateFormat: 'dd-mm-yy' });
		});
</script>

<h1>Place an ad</h1>
<hr />
														
<form:form method="post" modelAttribute="placeAdForm" action="/profile/placeAd"
	id="placeAdForm" autocomplete="off" enctype="multipart/form-data">

	<fieldset>
	<legend>General info</legend>
		<table style="width:50%">
			<tr>
				<td>
					<label for="field-title">Ad Title</label>
				</td>
				<td>
					<label for="type-room">Type:</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<form:input id="field-title" path="title" placeholder="Ad Title" />
				</td>
				<td>
					<form:radiobutton id="type-room" path="type" value="Room" checked="checked"/>Room 
					<form:radiobutton id="type-studio" path="type" value="Studio"/>Studio
				</td>
			</tr>
			
			<tr>
				<td>
					<label for="field-street">Street</label>
				</td>
				<td>
					<label for="field-city">City</label>
				</td>
			</tr>
			
			<tr>
				<td>
					<form:input id="field-street" path="street" placeholder="Street" />
				</td>
				<td>
					<form:input id="field-city" path="city" placeholder="City" />
					<form:errors path="city" cssClass="validationErrorText" />
				</td>
			</tr>
			
			<tr>
				<td>
					<label for="moveInDate">Move-in date</label>
				</td>
				<td>
					<label for="moveOutDate">Move-out date (optional)</label>
				</td>
			</tr>
			<tr>
				<td>
					<form:input type="text" id="field-moveInDate" path="moveInDate"/>
				</td>
				<td>
					<form:input type="text" id="field-moveOutDate" path="moveOutDate"/>
				</td>
			</tr>
			
			<tr>
				<td>
					<label for="field-Prize">Prize per month</label>
				</td>
				<td>
					<label for="field-SquareFootage">Square Meters</label>
				</td>
			</tr>
			<tr>
				<td>
					<form:input id="field-Prize" type="number" path="prize"	placeholder="Prize per month" step="50"/>
					<form:errors path="prize" cssClass="validationErrorText" />
				</td>
				<td>
					<form:input id="field-SquareFootage" type="number"
						path="squareFootage" placeholder="Prize per month" step="5" />
					<form:errors path="squareFootage" cssClass="validationErrorText" />
				</td>
			</tr>
			
			<tr>
				<td>
					<label for="field-smoker">Smoking inside allowed</label>
				</td>
				<td>
					<label for="field-animals">Animals allowed</label>
				</td>
			</tr>
			<tr>
				<td>
					<form:checkbox id="field-smoker" path="smoker" value="1" />
				</td>
				<td>
					<form:checkbox id="field-animals" path="animals" value="1" />
				</td>
			</tr>
			
		</table>
	</fieldset>
	

	<br />
	<fieldset>
		<legend>Room Description</legend>
		<form:textarea path="roomDescription" rows="10" cols="100" placeholder="Room Description" />
		<form:errors path="roomDescription" cssClass="validationErrorText" />
	</fieldset>

	<br />
	<fieldset>
		<legend>Roommates</legend>
		<form:textarea path="roommates" rows="10" cols="100" placeholder="Roommates" />
		<form:errors path="roommates" cssClass="validationErrorText" />
	</fieldset>

	<br />
	<fieldset>
		<legend>Preferences (optional)</legend>
		<form:textarea path="preferences" rows="5" cols="100" placeholder="Preferences"></form:textarea>
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
