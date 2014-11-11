<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<pre><a href="/">Home</a>   >   %%Profile page when done%% >  Edit profile</pre>

<%-- How do I call a requestMapper to get some things done without leaving the page
and without needing to click something? --%>
<script>
	$(document).ready(function() {
		$.get("/profile/editProfile/insertForm", function(data) {
			alert("insertForm");
			alert(data.getGender());
			
		});
		
		
		
</script>

<p><h3> This is a testpage </h3></p>
<p>${tester}</p>
<p>${currentUser.getPicture()}</p>

<c:import url="template/footer.jsp" />

