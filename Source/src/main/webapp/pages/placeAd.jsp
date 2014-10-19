<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="template/LLheader.jsp" />

<form:form method="post" modelAttribute="placeAdForm" action="/placeAd" id="placeAdForm" autocomplete="off">
	
	<%-- Rafi: Design isn't final, feel free to change (e.g. replace textareas by what eva) --%>
	
	<fieldset>
		<label>City</label>
			<form:input path="city" tabindex="1" placeholder="City"></form:input>
		<label for="field-Regon">Region</label>
			<form:input id="field-Region" type="text" path="region" tabindex="2" placeholder="Region"></form:input>
		
		<h4>Move in date</h4>
		
		<label for="field-DayMoveIn">Day</label>
			<form:select id="field-DayMoveIn" path="dayMoveIn" tabindex="3">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
			</form:select>
				
		<label for="field-MonthMoveIn">Month</label>
			<form:select id="field-MonthMoveIn" path="monthMoveIn" tabindex="4">
			<option value="1">Jan</option>
			<option value="2">Feb</option>
			<option value="3">März</option>
			<option value="4">April</option>
			<option value="5">Mai</option>
			<option value="6">Juni</option>
			<option value="7">Juli</option>
			<option value="8">Aug</option>
			<option value="9">Sep</option>
			<option value="10">Okt</option>
			<option value="11">Nov</option>
			<option value="12">Dez</option>
			</form:select>
			
		<label for="field-YearMoveIn" tabindex="4">Year</label>
			<form:select id="field-YearMoveIn" path="yearMoveIn" tabindex="5">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			</form:select>
		
		<h4>Move out date (optional)</h4>
		<label for="field-DayMoveOut">Day</label>
			<form:select id="field-DayMoveOut" path="dayMoveOut" tabindex="6">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
			</form:select>
		
				
		<label for="field-MonthMoveOut">Month</label>
			<form:select id="field-MonthMoveOut" path="monthMoveOut" tabindex="7">
			<option value="1">Jan</option>
			<option value="2">Feb</option>
			<option value="3">März</option>
			<option value="4">April</option>
			<option value="5">Mai</option>
			<option value="6">Juni</option>
			<option value="7">Juli</option>
			<option value="8">Aug</option>
			<option value="9">Sep</option>
			<option value="10">Okt</option>
			<option value="11">Nov</option>
			<option value="12">Dez</option>
			</form:select>
			
		<label for="field-YearMoveOut" tabindex="4">Year</label>
			<form:select id="field-YearMoveOut" path="yearMoveOut" tabindex="8">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			</form:select>
		
		<br>
		<label for="field-Prize">Prize per month</label>
			<form:input id="field-Prize" type="number" path="prize" min="1" tabindex="9" placeholder="Prize per month" ></form:input>
		
		<label for="field-SquareFootage">Square Footage</label>
			<form:input id="field-SquareFootage" type="number" path="squareFootage" min="1" tabindex="10" placeholder="Prize per month" ></form:input>
		
		
		<br>
		<fieldset>
			<legend>Room Description</legend>
			<form:textarea path="roomDescription" rows="10" cols="100" tabindex="11" 
				placeholder="Room Description"></form:textarea>
		</fieldset>
		
		<br>
		<fieldset>
			<legend>Roommates</legend>
			<form:textarea path="roommates" rows="10" cols="100" tabindex="15" 
				placeholder="Roommates"></form:textarea>
		</fieldset>
		
		<br>
		<fieldset>
			<legend>Preferences</legend>
			<form:textarea path="preferences" rows="5" cols="100" tabindex="14" 
				placeholder="Preferences"></form:textarea>
		</fieldset>
		
		<form action="demo_form.asp">
			<label for="form-Pictures">Pictures</label>
  			<input type="file" name="pic" id="form-Pictures" accept="image/*" tabindex="12">
  			<input type="submit" tabindex="13">
		</form>
		
		<br>
		<input type="checkbox" path="smokers" value="1">Smokers
		<input type="checkbox" path="animals" value="1">Animals
		
		<div class="form-actions">
            <button type="submit" class="btn btn-primary">Place Ad</button>
            <button type="button" class="btn">Cancel</button>
        </div>
		
	</fieldset>
</form:form>

<c:import url="template/LLfooter.jsp" />