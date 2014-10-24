<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="template/header.jsp" />

<h1>Detailed Ad Description page here</h1>

<%-- call via:
http://localhost:8080/profile/ad.?adId=1 
--%>

<%-- Test --%>
<h1>Id: ${shownAd.id}</h1>
<h1>City: ${shownAd.city}</h1>

<%-- mit ${shownUser.id} hole ich die variable id vom user welchen ich dem model unter dem Namen "shownUser" geaddet habe,
Methode profilePage() in IndexController.java model.addObject("shownUser", user); --%>
 
<%-- <h1>Id: ${shownUser.id}</h1>
<h1>First Name: ${shownUser.firstName}</h1>
<h1>Last Name: ${shownUser.lastName}</h1>
<h1>Email: ${shownUser.email}</h1>
<h1>Teamname: ${shownUser.team.teamName}</h1> --%>


<c:import url="template/footer.jsp" />
