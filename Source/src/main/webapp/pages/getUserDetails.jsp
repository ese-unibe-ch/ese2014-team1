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
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	ApplicationContext ac = RequestContextUtils
			.getWebApplicationContext(request);
	UserService userService = (UserService) ac
			.getBean(UserService.class);

	org.springframework.security.core.userdetails.User securityUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal();

	User realUser = userService.findUserByUsername(securityUser
			.getUsername());

	Picture picture = realUser.getPicture();
	String filePath;

	if (picture == null) {
		filePath = "/img/avatar.png";
	} else {
		filePath = picture.getFilePath();
	}

	out.print("<img src='" + filePath + "' />");

	out.print("<p class='text'>" + realUser.getFirstName() + "<br />"
			+ realUser.getLastName() + "</p>");
%>