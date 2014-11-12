<%@page import="ch.unibe.ese.team1.model.Picture"%>
<%@page import="ch.unibe.ese.team1.model.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="ch.unibe.ese.team1.model.Location"%>
<%@page import="java.util.List"%>
<%@page import="ch.unibe.ese.team1.controller.service.UserService"%>

<%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.context.ApplicationContext"%>

<!-- Import variables and classes, like a java import. -->
<%@include file='getUserPicture.jsp' %>

<%
	out.print("<img src='" + filePath + "' />");
	out.print("<p class='text'>" + realUser.getFirstName() + "<br />"
			+ realUser.getLastName() + "</p>");
%>