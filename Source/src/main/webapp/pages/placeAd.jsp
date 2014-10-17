<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="template/header.jsp" />
<c:import url="template/footer.jsp" />

<form:form method="post" modelAttribute="placeAdForm" action="index" id="placeAdForm" autocomplete="off">
	
	<fieldset>
	
		<div class="control-group">
		
			<label class="control-label" for="field-City">City</label>
				
				<form:input path="city" id="field-City" tabindex="1" placeholder="City"></form:input>
		</div>
		
	</fieldset>

</form:form>

