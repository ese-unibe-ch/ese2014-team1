<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<form:form method="post" modelAttribute="placeAdForm" action="/placeAd"
	id="placeAdForm" autocomplete="off" enctype="multipart/form-data">

	<%-- Rafi: Design isn't final, feel free to change (e.g. replace textareas by what eva) --%>

	<fieldset>
		<legend>General info</legend>
		<label>City</label>
		<form:input path="city" tabindex="1" placeholder="City" />
		<form:errors path="city" cssClass="validationErrorText" />
		<label for="field-Regon">Region</label>
		<form:input id="field-Region" type="text" path="region" tabindex="2"
			placeholder="Region" />
		<form:errors path="region" cssClass="validationErrorText" />
		<h2>Move in date</h2>

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
			<option value="1">January</option>
			<option value="2">February</option>
			<option value="3">March</option>
			<option value="4">April</option>
			<option value="5">May</option>
			<option value="6">June</option>
			<option value="7">July</option>
			<option value="8">August</option>
			<option value="9">September</option>
			<option value="10">October</option>
			<option value="11">November</option>
			<option value="12">December</option>
		</form:select>

		<label for="field-YearMoveIn">Year</label>
		<form:select id="field-YearMoveIn" path="yearMoveIn" tabindex="4">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
		</form:select>


		<h2>Move out date (optional)</h2>
		<label for="field-DayMoveOut">Day</label>
		<form:select id="field-DayMoveOut" path="dayMoveOut" tabindex="5">
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
		<form:select id="field-MonthMoveOut" path="monthMoveOut" tabindex="6">
			<option value="1">January</option>
			<option value="2">February</option>
			<option value="3">March</option>
			<option value="4">April</option>
			<option value="5">May</option>
			<option value="6">June</option>
			<option value="7">July</option>
			<option value="8">August</option>
			<option value="9">September</option>
			<option value="10">October</option>
			<option value="11">November</option>
			<option value="12">December</option>
		</form:select>

		<label for="field-YearMoveOut">Year</label>
		<form:select id="field-YearMoveOut" path="yearMoveOut" tabindex="7">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
		</form:select>

		<br />
		<br /> <label for="field-Prize">Prize per month</label>
		<form:input id="field-Prize" type="number" path="prize" tabindex="8"
			placeholder="Prize per month" />
		<form:errors path="prize" cssClass="validationErrorText" />

		<label for="field-SquareFootage">Square Footage</label>
		<form:input id="field-SquareFootage" type="number"
			path="squareFootage" tabindex="9" placeholder="Prize per month" />
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

	<fieldset>
		<legend>Pictures</legend>
		<label for="field-pictures">Pictures</label>
		<form:input type="file" path="pictures" id="field-pictures"
			accept="image/*" multiple="multiple" tabindex="13" />
		<form:errors path="pictures" cssClass="validationErrorText" />
	</fieldset>

	<br />
	<div>
		<button type="submit" tabindex="17">Place Ad</button>
		<button type="reset" tabindex="18">Cancel</button>
	</div>

</form:form>

<c:import url="template/footer.jsp" />
