<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="NavBar.css">
<title>KSK Travel -- Recommendations</title>
</head>
<body style="font-size: 12pt; text-align: left" bgcolor="#add8e6">
<strong><span style="font-size: 14pt">KSK Travel</span></strong>
<div class="topnav" id="myTopnav">
  <a href="CustomerMain.jsp">Home</a>
  <a href="CustomerReserve.jsp">Make a Reservation</a>
  <!-- <a href="CustomerAuctions.jsp">Auctions</a> -->
  <a href="CustomerHistory.jsp">History</a>
  <a class="active" href="${pageContext.request.contextPath}/recommendations">Recommendations</a>
</div>

<strong><span>Recommended For You</span></strong>
<table border="8" id="TABLE1" onclick="return TABLE1_onclick()">
	<tr>
	    <td style="width: 84px">
	        <span style="font-size: 10pt">Flight Number</span></td>
	    <td style="width: 84px">
	        <span style="font-size: 10pt">Airline</span></td>
	</tr>
	
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:forEach items="${recslist}" var="rec">
		<tr>
			<td style="width: 84px">
	            <span style="font-size: 10pt">${rec.flightNo}</span></td>
	        <td style="width: 187px">
	            <span style="font-size: 10pt">${rec.airline}</span></td>
		</tr>
	</c:forEach>
</table>

<br />
<input id="Button1" type="button" value="Logout" onclick="window.open('index.html','_self');" /><br />
</body>
</html>