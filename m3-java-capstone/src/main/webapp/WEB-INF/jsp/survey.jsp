<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Survey</title>
<c:import url="/WEB-INF/jsp/header.jsp">
	<c:param name="pageTitle" value="Survey" />
</c:import>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<c:url var="formUrl" value="/surveyResults" />
	<div class="container">
	<form action="${formUrl}" method="POST">
		<table class="surveyForm" style="padding-left: 20px">
				<c:if test="${not empty errorMessages}">
					<tr>
						<c:forEach var="message" items="${errorMessages}">
						<div class="alert alert-danger" role="alert">
							${message}
						</div>
						</c:forEach>
					</tr>
				</c:if>
			<tr>
				<th>Favorite National Park &nbsp;</th>
				<td><select name="parkCode">
						<option value="" disabled selected>--Select Park--</option>
						<c:forEach items="${parks}" var = "park">
							<c:choose>
         						<c:when test = "${park.parkCode == parkCode}">
          							<option value="${park.parkCode }" selected>${park.parkName }</option>
         						</c:when>
         						<c:otherwise>
         						<option value="${park.parkCode }">${park.parkName }</option>
         						</c:otherwise>
         					</c:choose>
						</c:forEach>
						
						
				</select></td>
			</tr>
			<tr>
				<th>Your Email</th>
				<td><input type="text" name="emailAddress" value="${emailAddress }"/></td>
			</tr>
			<tr>
				<th>State of Residence</th>
				<td><select name="state">
						<option value="${state}" disabled selected>--Select State--</option>
						<c:forEach items="${states}" var ="states">
							<c:choose>
         						<c:when test = "${states == state}">
          							<option value="${states }" selected>${states}</option>
         						</c:when>
         						<c:otherwise>
         						<option value="${states }">${states }</option>
         						</c:otherwise>
         					</c:choose>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>Activity Level</th>
				<td>
					<c:forEach items="${activityLevel}" var ="activityLevel">
							<c:choose>
         						<c:when test = "${activity == activityLevel}">
          							<input type="radio" name="activityLevel" value="${activityLevel }" checked> ${activityLevel } &nbsp;
         						</c:when>
         						<c:otherwise>
         						<input type="radio" name="activityLevel" value="${activityLevel }"> ${activityLevel } &nbsp;
         						</c:otherwise>
         					</c:choose>
						</c:forEach> 
				</td>
			</tr>
			<tr>
				<th></th>
				<td><br><input type="submit"></td>
			</tr>
		</table>

	</form>
	</div>
</body>
<c:import url="/WEB-INF/jsp/footer.jsp" />
</html>