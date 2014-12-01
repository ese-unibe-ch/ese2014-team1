<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="template/header.jsp" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test-Title</title>
</head>
<body>

<pre>Home</pre>

<h1>Our newest properties:</h1>

<div id="resultsDiv">
<div class="resultAd" data-price="400" data-movein="2014-12-15" data-age="2014-12-15">
					<div class="resultLeft">
						<a href="/ad?id=1"><img src="/img/test/ad1_1.jpg"></a>
						<h2>
							<a href="/ad?id=1">Roommate wanted in Bern</a>
						</h2>
						<p>Kramgasse 22, 3011 Bern</p>
						<br>
						<p>
							<i>
									
									Room
								</i>
						</p>
					</div>
					<div class="resultRight">
						<h2>CHF 400</h2>
						<br> <br>

						

						<p>Move-in date: 15.12.2014</p>
					</div>
				</div>
<div class="resultAd" data-price="480" data-movein="2015-01-01" data-age="2015-01-01">
					<div class="resultLeft">
						<a href="/ad?id=3"><img src="/img/test/ad3_1.jpg"></a>
						<h2>
							<a href="/ad?id=3">Nice, bright studio in the center of Basel</a>
						</h2>
						<p>Bruderholzstrasse 32, 4051 Basel</p>
						<br>
						<p>
							<i>
									Studio
									
								</i>
						</p>
					</div>
					<div class="resultRight">
						<h2>CHF 480</h2>
						<br> <br>

						

						<p>Move-in date: 01.01.2015</p>
					</div>
				</div>
<div class="resultAd" data-price="430" data-movein="2015-01-15" data-age="2015-01-15">
					<div class="resultLeft">
						<a href="/ad?id=4"><img src="/img/test/ad4_1.png"></a>
						<h2>
							<a href="/ad?id=4">Roommate wanted in Olten City</a>
						</h2>
						<p>Zehnderweg 5, 4600 Olten</p>
						<br>
						<p>
							<i>
									
									Room
								</i>
						</p>
					</div>
					<div class="resultRight">
						<h2>CHF 430</h2>
						<br> <br>

						

						<p>Move-in date: 15.01.2015</p>
					</div>
				</div>
<div class="resultAd" data-price="410" data-movein="2015-02-01" data-age="2015-02-01">
					<div class="resultLeft">
						<a href="/ad?id=5"><img src="/img/test/ad5_1.jpg"></a>
						<h2>
							<a href="/ad?id=5">Studio extrèmement bon marché à Neuchâtel</a>
						</h2>
						<p>Rue de l'Hôpital 11, 2000 Neuchâtel</p>
						<br>
						<p>
							<i>
									Studio
									
								</i>
						</p>
					</div>
					<div class="resultRight">
						<h2>CHF 410</h2>
						<br> <br>

						

						<p>Move-in date: 01.02.2015</p>
					</div>
				</div>
<c:import url="template/footer.jsp" /><br />


<a href="/signup"><button>Create Account</button></a><a href="/login"><button>Login</button></a><a href="/searchAd"><button>Search Flats</button></a>

</div>