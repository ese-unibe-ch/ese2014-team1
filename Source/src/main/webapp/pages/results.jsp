<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<!-- TODO: Add filter function -->

<h1>Search results:</h1>

<hr />

<c:choose>
	<c:when test="${empty results }">
		<p>No results found!
	</c:when>
	<c:otherwise>
		<div id="resultsDiv">
			<c:forEach var="ad" items="${results}">
				<div>
					<div class="resultLeft">
						<a href="<c:url value='/ad?id=${ad.id}' />"><img
							src="${ad.pictures[0].filePath}" /></a>
						<h2>
							<a href="<c:url value='/ad?id=${ad.id}' />">${ad.title }</a>
						</h2>
						<p>${ad.street},${ad.zipcode}${ad.city}</p>
						<br />
						<p>
							<i><c:choose>
									<c:when test="${ad.studio }">Studio</c:when>
									<c:otherwise>Room</c:otherwise>
								</c:choose></i>
						</p>
					</div>
					<div class="resultRight">
						<h2>CHF ${ad.prizePerMonth }</h2>
						<br /> <br />
						<p>Move in date: ${ad.moveInDate }</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:otherwise>
</c:choose>

<form:form method="post" modelAttribute="filterForm" action="/results"
	id="filterForm" autocomplete="off" enctype="multipart/form-data">

	<div id="filterDiv">
		<fieldset>
			<legend>Filter results:</legend>
			<h1>YOLO</h1>
		</fieldset>
	</div>
</form:form>

<c:import url="template/footer.jsp" />