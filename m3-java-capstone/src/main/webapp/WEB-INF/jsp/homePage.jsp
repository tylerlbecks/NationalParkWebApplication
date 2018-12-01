<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/jsp/header.jsp">
	<c:param name="pageTitle" value="Home Page" />
</c:import>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<body>

	<c:forEach items="${parks}" var="park">
		
		<c:url var="parkImage"
			value="img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />
		<c:url var="parkCode" value="/parkDetail?parkCode=${park.parkCode}" />
		<div style="display: inline-block">
			<div style="float: left">
				<a href="${parkCode}"><img src="${parkImage}" id="parkImage"></a>
			</div>
			<div id="parkSum">
				<h2>
					<a href="${parkCode}" id="parkName">${park.parkName}</a>
				</h2>
				<p>Location: ${park.state}</p>
				<p>${park.parkDescription}
				<p>
			</div>
		</div>
		<br>
	
	</c:forEach>
</body>

<c:import url="/WEB-INF/jsp/footer.jsp" />
</html>