<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="NavBar.css">
<title>KSK Travel -- Reservation</title>
<script type="text/javascript">
function visible(obj) {
	var type = obj;
	var retdate = document.getElementById("retdate");
	var retdatelabel = document.getElementById("retdatelabel");
	var selected = type.value;
	
	if(selected === '2'){
        retdate.style.display = "inline";
        retdatelabel.style.display = "inline";
        // retdate.style.visible = "visible";
        // retdatelabel.style.visible = "visible";
        // retdate.show();
        // retdatelabel.show();
    }
    else{
        retdate.style.display = "none";
        retdatelabel.style.display = "none";
        // retdate.style.visible = "hidden";
        // retdatelabel.style.visible = "hidden";
        // retdate.hide();
        // retdatelabel.hide();
    }
}
</script>
</head>
<body style="font-size: 12pt; text-align: left" bgcolor="#add8e6">
<strong><span style="font-size: 14pt">KSK Travel</span></strong>
<div class="topnav" id="myTopnav">
  <a href="CustomerMain.jsp">Home</a>
  <a class="active" href="CustomerReserve.jsp">Make a Reservation</a>
  <!-- <a href="CustomerAuctions.jsp">Auctions</a> -->
  <a href="CustomerHistory.jsp">History</a>
  <a href="${pageContext.request.contextPath}/recommendations">Recommendations</a>
</div>
<br/>
<div class="container">
<form name="myForm" action="reserve" method="post">
<div>
<label for="type" id="type">Type of trip: </label>
<select name="type" onchange="visible(this)">
  <option value="1">One-Way</option>
  <option value="2">Round Trip</option>
  <option value="3">Multi-City</option>
</select>
</div>
<div>
<label for="from">Departing from: </label>
<select name="from" id="from">
  <option value="01">Berlin Tegel</option>
  <option value="02">Chicago O'Hare International</option>
  <option value="03">Hartsfield-Jackson Atlanta Int</option>
  <option value="04">Ivato International</option>
  <option value="05">John F. Kennedy International</option>
  <option value="06">LaGuardia</option>
  <option value="07">Logan International</option>
  <option value="08">London Heathrow</option>
  <option value="09">Los Angeles International</option>
  <option value="10">San Francisco International</option>
  <option value="11">Tokyo International</option>
</select>
</div>
<div>
<label for="to">Destination: </label>
<select name="to">
  <option value="01">Berlin Tegel</option>
  <option value="02">Chicago O'Hare International</option>
  <option value="03">Hartsfield-Jackson Atlanta Int</option>
  <option value="04">Ivato International</option>
  <option value="05">John F. Kennedy International</option>
  <option value="06">LaGuardia</option>
  <option value="07">Logan International</option>
  <option value="08">London Heathrow</option>
  <option value="09">Los Angeles International</option>
  <option value="10">San Francisco International</option>
  <option value="11">Tokyo International</option>
</select>
</div>
<div>
<label for="depdate">Departure date: </label>
<input type="date" id="depdate" name="depdate">
</div>
<label for="retdate" id="retdatelabel" style="display: none">Return date: </label>
<input type="date" id="retdate" name="retdate" style="display: none">
<div>
<label for="airline">Airline: </label>
<select name="airline">
  <option value="01">Air Berlin</option>
  <option value="02">Air Japan</option>
  <option value="03">Air Madagascar</option>
  <option value="04">American Airlines</option>
  <option value="05">British Airways</option>
  <option value="06">Delta Airlines</option>
  <option value="07">JetBlue Airways</option>
  <option value="08">Lufthansa</option>
  <option value="09">Southwest Airlines</option>
  <option value="10">United Airlines</option>
</select>
</div>
<div>
<label for="meal">Meal: </label>
<input type="text" name="meal"> 
</div>
<br />
<input id="Button2" type="submit" value="Submit" />
</form>
</div>
<br />
<!-- <input id="Button2" type="button" value="Logout" onclick="window.open('index.html','_self');" /><br />  -->
</body>
</html>