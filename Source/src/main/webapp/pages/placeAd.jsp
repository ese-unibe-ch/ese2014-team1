<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="template/LLheader.jsp" />

<form:form method="post" modelAttribute="placeAdForm" action="index" id="placeAdForm" autocomplete="off">
	
	<%-- Design isn't final, feel free to change. --%>
	
	<fieldset>
		<label>City</label>
			<form:input path="city" tabindex="1" placeholder="City"></form:input>
		<label for="field-Regon">Region</label>
			<form:input id="field-Region" type="text" path="region" tabindex="10" placeholder="Region"></form:input>
		
		<h4>Move in date</h4>
		
		<label for="field-DayMoveIn">Day</label>
			<form:select id="field-DayMoveIn" path="dayMoveIn" tabindex="2">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="etc">etc</option>
			</form:select>
				
		<label for="field-MonthMoveIn">Month</label>
			<select id="field-MonthMoveIn" path="monthMoveIn" tabindex="3">
			<option value="Jan">Jan</option>
			<option value="Feb">Feb</option>
			<option value="März">März</option>
			<option value="etc">etc</option>
			</select>
			
		<label for="field-YearMoveIn" tabindex="4">Year</label>
			<select id="field-YearMoveIn" path="yearMoveIn" tabindex="4">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="etc">etc</option>
			</select>
		
		<h4>Move out date (optional)</h4>
		<label for="field-DayMoveOut">Day</label>
			<form:select id="field-DayMoveOut" path="dayMoveOut" tabindex="5">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="etc">etc</option>
			</form:select>
		
				
		<label for="field-MonthMoveOut">Month</label>
			<select id="field-MonthMoveOut" path="monthMoveOut" tabindex="6">
			<option value="Jan">Jan</option>
			<option value="Feb">Feb</option>
			<option value="März">März</option>
			<option value="etc">etc</option>
			</select>
			
		<label for="field-YearMoveOut" tabindex="4">Year</label>
			<select id="field-YearMoveOut" path="yearMoveOut" tabindex="7">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="etc">etc</option>
			</select>
		
		<br>
		<label for="field-Prize">Prize per month</label>
			<form:input id="field-Prize" type="number" path="prize" min="1" tabindex="8" placeholder="Prize per month" ></form:input>
		
		<label for="field-SquareFootage">Square Footage</label>
			<form:input id="field-SquareFootage" type="number" path="squareFootage" min="1" tabindex="9" placeholder="Prize per month" ></form:input>
		
		
		<br>
		<fieldset>
			<legend>Room Description</legend>
			<form:textarea path="roomDescription" rows="10" cols="100" tabindex="11" 
				placeholder="Room Description"></form:textarea>
		</fieldset>
		
		<form action="demo_form.asp">
			<label for="form-Pictures">Pictures</label>
  			<input type="file" name="pic" id="form-Pictures" accept="image/*" tabindex="12">
  			<input type="submit" tabindex="13">
		</form>
	
		<br>
		<fieldset>
			<legend>Preferences</legend>
			<form:textarea path="preferences" rows="5" cols="100" tabindex="14" 
				placeholder="Preferences"></form:textarea>
		</fieldset>
	
		<br>
		<fieldset>
			<legend>Roommates</legend>
			<form:textarea path="roommates" rows="10" cols="100" tabindex="11" 
				placeholder="Roommates"></form:textarea>
		</fieldset>
		
		<input type="checkbox" path="smokers" value="1">Smokers
		<input type="checkbox" path="animals" value="1">Animals 
		
	</fieldset>

</form:form>

<c:import url="template/LLfooter.jsp" />
