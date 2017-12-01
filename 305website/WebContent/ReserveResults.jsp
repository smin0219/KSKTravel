<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="NavBar.css">
<title>KSK Travel -- Home</title>
</head>
<body style="font-size: 12pt; text-align: left" bgcolor="#add8e6">
<strong><span style="font-size: 14pt">KSK Travel</span></strong>
<div class="topnav" id="myTopnav">
  <a class="active" href="CustomerMain.jsp">Home</a>
  <a href="CustomerReserve.html">Make a Reservation</a>
  <a href="CustomerAuctions.jsp">Auctions</a>
  <a href="CustomerHistory.jsp">History</a>
  <a href="CustomerRecommendations.jsp">Recommendations</a>
</div>
<br />

<strong><span>Available Flights</span></strong>
<table border="8" id="TABLE1" onclick="return TABLE1_onclick()">
	<tr>
	    <td style="width: 84px">
	        <span style="font-size: 10pt">Departing Time</span></td>
	    <td style="width: 187px">
	        <span style="font-size: 10pt">Departing Airport</span></td>
	    <td style="width: 74px">
	        <span style="font-size: 10pt">Destination Airport</span></td>
	    <td>
	        <span style="font-size: 10pt">Flight Number</span></td>
	    <td style="width: 7px">
	        <span style="font-size: 10pt">Airline</span></td>
	    <td style="width: 7px">
	        <span style="font-size: 10pt">Fare</span></td>
	</tr>
	
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:forEach items="${leglist}" var="leg">
		<tr>
			<td style="width: 84px">
	            <span style="font-size: 10pt">${leg.depTime}</span></td>
	        <td style="width: 187px">
	            <span style="font-size: 10pt">${leg.depAirport}</span></td>
	        <td style="width: 74px">
	            <span style="font-size: 10pt">${leg.arrAirport}</span></td>
	        <td>
	            <span style="font-size: 10pt">${leg.flightNo}</span></td>
	        <td style="width: 7px">
	            <span style="font-size: 10pt">${leg.airline}</span></td>
	        <td style="width: 7px">
	            <span style="font-size: 10pt">${leg.fare}</span></td>
	        <td style="width: 7px">
	        	<form name="myForm" action="submitres" method="post">
	        		<input type="hidden" name="depTime" value="${leg.depTime}" />
	        		<input type="hidden" name="depAirport" value="${leg.depAirport}" />
	        		<input type="hidden" name="arrAirport" value="${leg.arrAirport}" />
	        		<input type="hidden" name="flightNo" value="${leg.flightNo}" />
	        		<input type="hidden" name="airline" value="${leg.airline}" />
	        		<input type="hidden" name="fare" value="${leg.fare}" />
	        		<input type="hidden" name="legNo" value="${leg.legNo}" />
	        		<input type="hidden" name="arrTime" value="${leg.arrTime}" />
	        		<input type="hidden" name="meal" value="${leg.meal}" />
			    	<input id="Button2" type="submit" value="Select" />
				</form>
	        </td>
		</tr>
	</c:forEach>
</table>
<br />
<input id="Button1" type="button" value="Cancel" onclick="window.open('CustomerReserve.html','_self');" /><br />
</body>
</html>