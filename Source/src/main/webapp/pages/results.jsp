<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />
			
	<!-- TODO: Add filter function -->		
			
	<h1>Search results:</h1>
	
	<hr />
	
	<table id="resultTable">
	<tr><th>City</th><th>Title</th><th>Type</th><th>Price</th></tr>
	<c:forEach var="ad" items="${results}">
  	<tr><td>${ad.city}</td><td><a href="<c:url value='/ad?id=${ad.id}' />">${ad.title}</a></td><td>${ad.type}</td><td>${ad.prizePerMonth}</td></tr>
	</c:forEach>
	</table>
	
<c:import url="template/footer.jsp" />