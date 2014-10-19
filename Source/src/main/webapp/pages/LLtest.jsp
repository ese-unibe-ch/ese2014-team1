<%-- black magic --%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/LLheader.jsp" />

<%-- body tag already opened in header, will be closed in footer --%>

<p>Ab hier ist nicht mehr Header.</p>
<p>Try to scroll a bit!</p>
<p>Header and footer will stay in place, rest scrolls.</p>

<c:import url="template/LLfooter.jsp" />