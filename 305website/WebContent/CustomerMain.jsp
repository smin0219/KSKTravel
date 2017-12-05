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
  <a href="CustomerReserve.jsp">Make a Reservation</a>
  <!-- <a href="CustomerAuctions.jsp">Auctions</a> -->
  <a href="CustomerHistory.jsp">History</a>
  <a href="${pageContext.request.contextPath}/recommendations">Recommendations</a>
</div>
<br />

<strong><span>Your Reservations</span></strong>
<table border="8" id="TABLE1" onclick="return TABLE1_onclick()">
	<tr>
	    <td style="width: 84px">
	        <span style="font-size: 10pt">Reservation Number</span></td>
	    <td style="width: 187px">
	        <span style="font-size: 10pt">Reservation Date</span></td>
	    <td style="width: 74px">
	        <span style="font-size: 10pt">Departing</span></td>
	    <td>
	        <span style="font-size: 10pt">Destination</span></td>
	    <td style="width: 7px">
	        <span style="font-size: 10pt">Flight Number</span></td>
	    <td style="width: 7px">
	        <span style="font-size: 10pt">Seat</span></td>
	    <td style="width: 7px">
	        <span style="font-size: 10pt">Meal</span></td>
	    <td style="width: 187px">
	        <span style="font-size: 10pt">Departure Time</span></td>
	    <td style="width: 84px">
	        <span style="font-size: 10pt">Trip Type</span></td>
	</tr>
	
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:forEach items="${reslist}" var="res">
		<tr>
			<td style="width: 84px">
	            <span style="font-size: 10pt">${res.number}</span></td>
	        <td style="width: 187px">
	            <span style="font-size: 10pt">${res.date}</span></td>
	        <td style="width: 74px">
	            <span style="font-size: 10pt">${res.departing}</span></td>
	        <td>
	            <span style="font-size: 10pt">${res.destination}</span></td>
	        <td style="width: 7px">
	            <span style="font-size: 10pt">${res.flightnum}</span></td>
	        <td style="width: 7px">
	            <span style="font-size: 10pt">${res.seat}</span></td>
	        <td style="width: 7px">
	            <span style="font-size: 10pt">${res.meal}</span></td>
	        <td style="width: 7px">
	            <span style="font-size: 10pt">${res.deptime}</span></td>
	        <td style="width: 84px">
	            <span style="font-size: 10pt">${res.type}</span></td>
		</tr>
	</c:forEach>
</table>
<br />
<input id="Button1" type="button" value="Logout" onclick="window.open('index.html','_self');" /><br />
</body>
</html>