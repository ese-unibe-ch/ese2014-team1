<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="ch.unibe.ese.team1.model.Location"%>
<%@page import="java.util.List"%>
<%@page import="ch.unibe.ese.team1.controller.service.GeoDataService"%>
<%@page import="java.util.Collections" %>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	ApplicationContext ac = RequestContextUtils
			.getWebApplicationContext(request);
	GeoDataService geoDataService = (GeoDataService) ac
			.getBean(GeoDataService.class);

	List<Location> zipCodes = geoDataService.getAllLocations();
	Set<String> cityNames = new HashSet<>();
	Iterator<Location> iterator = zipCodes.iterator();
	
	while(iterator.hasNext()){
		Location location = iterator.next();
		cityNames.add(location.getCity());
	}
	
	List<String> sortedCityNames = new ArrayList<>(cityNames);
	Collections.sort(sortedCityNames);
	Iterator<String> cityIterator = sortedCityNames.iterator();
	
	out.print("[");
	String zip = cityIterator.next();
	out.print("\"" + zip + "\"");
	while (cityIterator.hasNext()) {
		zip = cityIterator.next();
		out.print(",\"" + zip + "\"");
	}
	out.print("]");
%>