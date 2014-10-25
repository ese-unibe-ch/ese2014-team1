<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="template/header.jsp" />

<%-- call via:
http://localhost:8080/profile/ad.?adId=1 
--%>

<h1>${shownAd.title}</h1>
<hr />

<table style="width:50%">
  <tr>
    <td><h2>Address</h2></td>
    <td>${shownAd.street}, ${shownAd.zipcode}</td>
  </tr>
  
  <tr>
    <td><h2>Move-in Date</h2></td>
    <td>${shownAd.moveInDate}</td>
  </tr>
  
  <tr>
    <td><h2>Move-out Date</h2></td>
    <td>${shownAd.moveInDate}</td>
  </tr>
  
  <tr>
    <td><h2>Prize</h2></td>
    <td>${shownAd.prizePerMonth}</td>
  </tr>
  
  <tr>
    <td><h2>Square Footage</h2></td>
    <td>${shownAd.squareFootage}</td>
  </tr>
</table> 

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
