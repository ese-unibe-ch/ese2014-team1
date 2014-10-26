<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />
															
<form:form method="post" modelAttribute="searchForm" action="/results"
	id="searchForm" autocomplete="off" enctype="multipart/form-data">

	<fieldset>
		<legend>Search for a flat</legend>
		
		<form:radiobutton path="type" value="Room" checked="checked"/>Room 
		<form:radiobutton path="type" value="Studio"/>Studio
		<form:radiobutton path="type" value="both"/>Both<br>
		
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