
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
    
    

    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="/WEB-INF/jsp/header.jsp">
<c:param name="pageTitle" value="Park Details"/>
</c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${park.parkName}</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
<div class="center">
<div class="center">
<h1>${park.parkName }</h1>
<p>${park.state}<i>, founded in ${park.yearFounded}</i></p>

<c:url var="parkImage" value="img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />
			<img id="detailImg" src="${parkImage}"/>
			<br>
			<br>
<p id="quoteWidth">"${park.inspQuote}"</p>
<p>-${park.inspQuoteSource}</p>
</div>
<div>
<hr width="60%">
<h2>Visitor Information</h2>
<table class="centerTable center">
<tr>
	<th class="tablePadding">Entry Fee</th>
	<th class="tablePadding">Annual Visitors</th>
	<th class="tablePadding">Number of Campsites</th>
	<th class="tablePadding">Animal Species</th>
</tr>
<tr>
	<td>$${park.entryFee}</td>
	<td>${park.visitorC}</td>
	<td>${park.campsiteString}</td>
	<td>${park.numOfAnimalSpecies}</td>
</tr>
</table>
</div>
<br>
<div>
<hr width="60%">
<h2>Geographical Information</h2>
<table class="centerTable center">
<tr>
	<th class="tablePadding">Climate</th>
	<th class="tablePadding">Acres</th>
	<th class="tablePadding">Elevation</th>
	<th class="tablePadding">Mile of Trail</th>
</tr>
<tr>
	<td>${park.climate}</td>
	<td>${park.acreageString}</td>
	<td>${park.elevationString} feet</td>
	<td>${park.milesOfTrail} miles</td>
</tr>
</table>
</div>
</div>
<br>
<hr width="60%">
	<h2 class="center">Forecast</h2>
	<div class="center">
	<c:url value = "/parkDetailsTempScale" var = "parkDetailsTemp"/>
<form method = "GET" action= "${parkDetailsTemp}">

		<button type="submit" name="tempScale" value="fahrenheit" id="fahrenheit">Fahrenheit</button>
		<button type="submit" name="tempScale" value="celsius" id="celsius">Celsius</button>
		
		</form>
		</div>
	<br>
	<div class="center" style="display: flex; overflow-x: auto; direction: ltr">
<c:forEach items = "${weather}" var = "weather">
	<div class="floatLeft center weatherWidth">
		<h3>${weather.dow}</h3>	
	       		<c:url var = "weatherImage" value = "img/weather/${weather.forecastFile }.png"/>
	  
		<div class="card" style="width: 18rem; height:450px">
			<img class="card-img-top" src="${weatherImage }" id = "weatherImage"/>
			<p><b class="card-title">${fn:toUpperCase(weather.forecast)}</b></p>
				<p><b class="card-title">High: ${weather.decimalStringHigh} &deg;${weather.scale}&nbsp;&nbsp; Low: ${weather.decimalStringLow} &deg;${weather.scale}</b></p>
			<p class="card-text">${weather.recommendation} ${weather.tempRecommendation}</p>
		</div>
	</div>
	
</c:forEach>
</div>




</body>
<c:import url="/WEB-INF/jsp/footer.jsp" />
</html>