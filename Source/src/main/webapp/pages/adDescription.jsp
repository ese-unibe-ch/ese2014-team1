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
<h1>Zipcode: ${shownAd.zipcode}</h1>


<!-- 
	private long id;
	private String title;
	private int zipcode;
	private Date moveInDate;
	private Date moveOutDate;
	private int prizePerMonth;
	private int squareFootage;
	private String roomDescription;
	private String preferences;
	private String roommates;
	private boolean smoker;
	private boolean animals;
	private Set<AdPicture> pictures;
	private User user;
 -->

<c:import url="template/footer.jsp" />
