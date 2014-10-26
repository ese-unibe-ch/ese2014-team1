<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />
			
			
	<h1>Search results</h1>

	<table>
	<tr><th>Title</th><th>Id</th></tr>
	<c:forEach var="ad" items="${allAds}">
  	<tr><td>${ad.title} </td><td>${ad.id}</td></tr>
	</c:forEach>
	</table>



	<!-- 														
	<form:form method="post" modelAttribute="resultForm" action="/results"
	id="resultForm" autocomplete="off" enctype="multipart/form-data">

	<!-- implement the filter function here. the action is already directed at /results
	(ergo stays on the same page) -->
	
</form:form>

<c:import url="template/footer.jsp" />