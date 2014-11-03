<%@page import="java.util.Iterator"%>
<%@page import="ch.unibe.ese.team1.model.Location"%>
<%@page import="java.util.List"%>
<%@page import="ch.unibe.ese.team1.controller.service.UserService"%>
<%@page import="ch.unibe.ese.team1.model.User" %>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	String email = request.getParameter("email");
	out.print("test" + email);

	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	UserService userService = (UserService) ac.getBean(UserService.class);
	
	User user = userService.findUserByUsername("tz.kolonko@gmail.com");
	
	if (user != null) {
		out.print("1");
	} else
		out.print("0");
	
%>