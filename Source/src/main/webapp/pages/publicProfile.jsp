<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<c:import url="template/header.jsp" />

<pre><a href="/">Home</a>   >   Public Profile</pre>

<h1>Edit your Profile</h1>
<hr />

<!-- check if user is logged in -->
<security:authorize var="loggedIn" url="/profile" />


<c:choose>
	<c:when test="${loggedIn}">
		<a id="profile_picture_publicProfile"> <c:import
					url="/pages/getUserPicture.jsp" />
		</a>
	</c:when>
	<c:otherwise>
		<a href="/login">Login</a>
	</c:otherwise>
</c:choose>
<table class="editProfileTable">
	<tr>
		<td class="spacingTable"><label for="user-name">Username:</label><a>&emsp;</a>
		<a id="user-name">${currentUser.username}</a></td>
		
	</tr>
	<tr>
		<td class="spacingTable"><label for="first-name">First name:</label><a>&emsp;</a>
		<a id="first-name">${currentUser.firstName}"</a></td>
	</tr>
	<tr>	
		<td class="spacingTable"><label for="last-name">Last name:</label><a>&emsp;</a>
		<a id="last-name">${currentUser.lastName}</a></td>
	</tr>
	<tr>	
		<td class="spacingTable"><label for="password">Password:</label><a>&emsp;&thinsp;</a>
		<a id="password">${currentUser.password}</a></td>
	</tr>
		
 	<tr>	
		<td class="spacingTable"><label for="gender">Gender:</label><a>&emsp;&emsp;&thinsp;</a>
		<a id="gender">${currentUser.gender}</a></td>
	</tr>
	<tr>
		<td class="spacingTable"><label for="about-me">About me:</label><a>&emsp;&thinsp;</a><br>
		<a id="about-me" >${currentUser.aboutMe}"</a></td>
	</tr>
</table>
<div>
		<a class="button" href="/profile/editProfile">Edit Profile</a>
</div>


<c:import url="template/footer.jsp" />