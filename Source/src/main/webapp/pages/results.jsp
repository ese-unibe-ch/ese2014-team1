<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />
															
<form:form method="post" modelAttribute="resultForm" action="/results"
	id="resultForm" autocomplete="off" enctype="multipart/form-data">

	You will see flats here.
	
</form:form>

<c:import url="template/footer.jsp" />