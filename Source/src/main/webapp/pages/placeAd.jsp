<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="template/LLheader.jsp" />

<form:form method="post" modelAttribute="placeAdForm" action="index" id="placeAdForm" autocomplete="off">
	
	<fieldset>
		<div class="control-group">
		
			<label>City</label>
				<form:input path="city" tabindex="1" placeholder="City"></form:input>
		</div>
		
		<h4>Move in date</h4>
		
		<label for="field-DayMoveIn">Day</label>
			<form:select name="field-DayMoveIn" path="dayMoveIn" tabindex="2">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="etc">etc</option>
			</form:select>
		
				
		<label for="field-MonthMoveIn">Month</label>
			<select name="field-MonthMoveIn" path="monthMoveIn" tabindex="3">
			<option value="Jan">Jan</option>
			<option value="Feb">Feb</option>
			<option value="März">März</option>
			<option value="etc">etc</option>
			</select>
			
		<label for="field-YearMoveIn" tabindex="4">Year</label>
			<select name="field-YearMoveIn" path="yearMoveIn" tabindex="4">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="etc">etc</option>
			</select>
		
		<h4>Move out date (optional)</h4>
		<label for="field-DayMoveOut">Day</label>
			<form:select name="field-DayMoveOut" path="dayMoveOut" tabindex="5">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="etc">etc</option>
			</form:select>
		
				
		<label for="field-MonthMoveOut">Month</label>
			<select name="field-MonthMoveOut" path="monthMoveOut" tabindex="6">
			<option value="Jan">Jan</option>
			<option value="Feb">Feb</option>
			<option value="März">März</option>
			<option value="etc">etc</option>
			</select>
			
		<label for="field-YearMoveOut" tabindex="4">Year</label>
			<select name="field-YearMoveOut" path="yearMoveOut" tabindex="7">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="etc">etc</option>
			</select>
		
		<br>
		<label for="field-Prize">Prize per month</label>
			<form:input name="field-Prize" type="number" path="prize" min="1" tabindex="8" placeholder="Prize per month" ></form:input>
		
		<label for="field-Regon">Region</label>
			<form:input name="field-Region" type="text" path="region" tabindex="9" placeholder="Region"></form:input>
		
		<br>
		<fieldset>
			<legend>Room Description:</legend>
			<form:textarea path="roomDescription" id="field-RoomDescription" rows="10" cols="100" tabindex="10" 
				placeholder="Room Description"></form:textarea>
		</fieldset>
		

	<%-- 
	<label align="right" for="field-RoomDescription">Room Description</label>
			<form:textarea path="roomDescription" id="field-RoomDescription" rows="10" cols="100" tabindex="10" 
				placeholder="Room Description"></form:textarea>
	--%>
	
	
		
	</fieldset>

</form:form>

<c:import url="template/searchFooter.jsp" />
