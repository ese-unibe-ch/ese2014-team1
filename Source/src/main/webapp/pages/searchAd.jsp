<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />
															
<form:form method="get" modelAttribute="searchForm" action="/results"
	id="searchForm" autocomplete="off" enctype="multipart/form-data">

	<fieldset>
		<legend>Search for a flat</legend>
		<label for="type">Room</label>
		<input type="checkbox" id="field-room" path="room" value="1" tabindex="1" />
		<label for="field-room">Studio</label>
		<input type="checkbox" id="field-studio" path="studio" value="1" tabindex="2" />
		
		<br />
		
		<label for="city">City</label>
		<input type="text" name="city" id="city" placeholder="e.g. Bern" tabindex="3" />
		
		<!-- Had the weirdest bug here. Only the second one of two range input forms would work, regardless
		of the parameters. So we now have one which is simply invisible until we fix the bug, or forever. -->
		<div style="display:none">
		<form oninput="blah.value=prizeMONGO.value" id="SPAST">
        <input type="hidden" name="prizeMONGO" id="prizeMONGO" min="0" max="2000" step="50" value="500" tabindex="4">
        <output name="blah" for="prizeMONGO">0</output>
    	</form></div>
    	
		<form oninput="radius.value=radiusInput.value">
		<label for="radius">Within radius of (max.):</label>
        <input type="range" id="radiusInput" name="radiusInput" min="0" max="50" step="5" value="10" tabindex="5">
        <output name="radius" for="radiusInput">20</output> km
        </form>
    	
    	<form oninput="prize.value=prizeInput.value">
    	<label for="prize">Price (max.):</label>
        <input type="range" id="prizeInput" name="prizeInput" min="0" max="2000" step="50" value="500" tabindex="6">
        <output name="prize" for="prizeInput">500</output> Chf
        </form> 
        
        <!-- why does the reset button not reset all the input fields? -->
        <button type="submit" tabindex="7">Search</button>
		<button type="reset" tabindex="8">Cancel</button>      
        
	</fieldset>
	
</form:form>

<c:import url="template/footer.jsp" />
