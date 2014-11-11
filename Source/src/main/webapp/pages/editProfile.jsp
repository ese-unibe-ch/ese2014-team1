<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<c:import url="template/header.jsp" />

<pre><a href="/">Home</a>   >   %%Profile page when done%% >  Edit profile</pre>

<%-- How do I call a requestMapper to get some things done without leaving the page
and without needing to click something? --%>
<script>
	$(document).ready(function() {
		$.get("/profile/editProfile/insertForm", function(data) {
			
		});
		
	});
		
		
		
</script>

<h1>Edit your Profile</h1>
<hr />

<!-- check if user is logged in -->
<security:authorize var="loggedIn" url="/profile" />


<c:choose>
	<c:when test="${loggedIn}">
		<a id="profile_picture_editPage"> <c:import
					url="/pages/getUserDetails.jsp" />
		</a>
	</c:when>
	<c:otherwise>
		<a href="/login">Login</a>
	</c:otherwise>
</c:choose>

<form:form method="post" modelAttribute="editProfileForm"
	action="/profile/editProfile" id="editProfileForm" autocomplete="off"
	enctype="multipart/form-data">

<table class="editProfileTable">
	<tr>
		<td class="spacingTable"><label for="first-name">First name:</label><a>&emsp;</a>
		<form:input id="first-name" path="firstName" value="${currentUser.firstName}" /></td>
	</tr>
	<tr>	
		<td class="spacingTable"><label for="last-name">Last name:</label><a>&emsp;</a>
		<form:input id="last-name" path="lastName" value="${currentUser.lastName}" /></td>
	</tr>
	<tr>	
		<td class="spacingTable"><label for="password">Password:</label><a>&emsp;&thinsp;</a>
		<form:input type="password" id="password" path="password" value="${currentUser.password}" /></td>
	</tr>
	<tr>	
		<td class="spacingTable"><label for="gender">Gender:</label><a>&emsp;&emsp;&thinsp;</a>
		<form:input id="gender" path="gender" value="${currentUser.gender}" /></td>
	</tr>
	<tr>
		<td class="spacingTable"><label for="changePicture">New picture:</label><a>&thinsp;</a>
		<input type="file" id="changePicture" accept="image/*" /></td>
	</tr>
	<tr>
		<td class="spacingTable"><label for="about-me">About me:</label><a>&emsp;&thinsp;</a><br>
		<form:textarea id="about-me" path="aboutMe" rows="10" cols="100" value="${currentUser.aboutMe}" /></td>
	</tr>
</table>

</form:form>


<c:import url="template/footer.jsp" />

