<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />
															
<form:form method="post" modelAttribute="searchForm" action="/searchAd"
	id="searchForm" autocomplete="off" enctype="multipart/form-data">

	<fieldset>
		<legend>Search stuff</legend>
		<label for="type">Room</label>
		<form:checkbox id="field-room" path="room" value="1" tabindex="15" />
		<label for="field-room">Studio</label>
		<form:checkbox id="field-studio" path="studio" value="1" tabindex="16" />
	</fieldset>
	
</form:form>

<c:import url="template/footer.jsp" />
