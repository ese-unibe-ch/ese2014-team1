<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<script>
		$(document).ready(function(){
			$("#field-city").autocomplete();
			$("#field-city").autocomplete({source: <c:import url="getzipcodes.jsp" />});
			$("#field-city").autocomplete("enable");
			$("#field-moveInDate").datepicker({ dateFormat: 'dd-mm-yy' });
			$("#field-moveOutDate").datepicker({ dateFormat: 'dd-mm-yy' });
		});
</script>
															
<form:form method="post" modelAttribute="placeAdForm" action="/profile/placeAd"
	id="placeAdForm" autocomplete="off" enctype="multipart/form-data">

	<fieldset>
		<legend>General info</legend>
		<label for="field-city">City</label>
		<form:input id="field-city" path="city" tabindex="1" placeholder="City" />
		
		<form:errors path="city" cssClass="validationErrorText" />
		<label for="field-Region">Region</label>
		<form:input id="field-Region" type="text" path="region" tabindex="2"
			placeholder="Region" />
		<form:errors path="region" cssClass="validationErrorText" />
		<h2>Move in date</h2>
		
		<label for="moveInDate">Move-in date:</label>
		<form:input type="text" id="field-moveInDate" path="moveInDate"/>
		
		<label for="moveOutDate">Move-out date:</label>
		<form:input type="text" id="field-moveOutDate" path="moveOutDate"/>

		<br />
		<br /> 
		<label for="field-Prize">Prize per month</label>
		<form:input id="field-Prize" type="number" path="prize" tabindex="8"
			placeholder="Prize per month" step="50"/>
		<form:errors path="prize" cssClass="validationErrorText" />

		<label for="field-SquareFootage">Square Meters</label>
		<form:input id="field-SquareFootage" type="number"
			path="squareFootage" tabindex="9" placeholder="Prize per month" step="5" />
		<form:errors path="squareFootage" cssClass="validationErrorText" />

		<br />
		<br /> <label for="field-smoker">Smokers</label>
		<form:checkbox id="field-smoker" path="smoker" value="1" tabindex="15" />
		<label for="field-animals">Animals</label>
		<form:checkbox id="field-animals" path="animals" value="1"
			tabindex="16" />
	</fieldset>

	<br />
	<fieldset>
		<legend>Room Description</legend>
		<form:textarea path="roomDescription" rows="10" cols="100"
			tabindex="10" placeholder="Room Description" />
		<form:errors path="roomDescription" cssClass="validationErrorText" />
	</fieldset>

	<br />
	<fieldset>
		<legend>Roommates</legend>
		<form:textarea path="roommates" rows="10" cols="100" tabindex="11"
			placeholder="Roommates" />
		<form:errors path="roommates" cssClass="validationErrorText" />
	</fieldset>

	<br />
	<fieldset>
		<legend>Preferences</legend>
		<form:textarea path="preferences" rows="5" cols="100" tabindex="12"
			placeholder="Preferences"></form:textarea>
	</fieldset>


	<script>
	$(document).ready(function(){
		        var scntDiv = $('#p_scents');
		        var i = $('#p_scents p').size() + 1;
		        
		        $('#addScnt').live('click', function() {
		        
		        		$('<label for="field-pictures"><form:input type="file" path="cauliflower" id="field-pictures" accept="image/*" name="field-pictures_' + i +'" value="" tabindex="13" /></label> <a href="#" id="remScnt">Remove</a></p>').appendTo(scntDiv);
		                
		                i++;
		                return false;
		        });
		        
		        $('#remScnt').live('click', function() { 
		                if( i > 2 ) {
		                        $(this).parents('p').remove();
		                        i--;
		                }
		                return false;
		        });
	
			});
		</script>


	<fieldset id="p_scents">
		<legend>Pictures</legend>
		
		<a href="#" id="addScnt">Add more pictures</a>
		<label for="field-pictures">Pictures</label>
		<form:input type="file" path="pictures" id="field-pictures"
			accept="image/*" multiple="multiple" tabindex="13" />
		<form:errors path="pictures" cssClass="validationErrorText" />
	
		<br>
	</fieldset>
	
	
	<br />
	<div>
		<button type="submit" tabindex="17">Place Ad</button>
		<button type="reset" tabindex="18">Cancel</button>
	</div>

</form:form>

<c:import url="template/footer.jsp" />
