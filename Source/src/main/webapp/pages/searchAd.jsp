<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />
															
<form:form method="post" modelAttribute="searchForm" action="/results"
	id="searchForm" autocomplete="off" enctype="multipart/form-data">

	<fieldset>
		<legend>Search for a flat</legend>
		<label for="type">Room</label>
		<form:checkbox id="field-room" path="room" value="1" tabindex="1" />
		<label for="field-room">Studio</label>
		<form:checkbox id="field-studio" path="studio" value="1" tabindex="2" /><br />
		
		<label for="city">City</label>
		<form:input type="text" name="city" id="city" path="city" placeholder="e.g. Bern" tabindex="3" />
		
		<label for="radius">Within radius of (max.):</label>
		<form:input id="radiusInput" type="number" path="radius" placeholder="e.g. 5" step="5" value="5"/>km
		<form:errors path="radius" cssClass="validationErrorText" /><br />
		
		<label for="prize">Price (max.):</label>
		<form:input id="prizeInput" type="number" path="prize" placeholder="e.g. 5" step="50" value="500"/>Chf
		<form:errors path="prize" cssClass="validationErrorText" /><br />
        
        <button type="submit" tabindex="7">Search</button>
		<button type="reset" tabindex="8">Cancel</button>      
	</fieldset>
	
</form:form>

<c:import url="template/footer.jsp" />