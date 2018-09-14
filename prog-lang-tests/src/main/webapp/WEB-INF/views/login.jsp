<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="resources/css/main.css" type="text/css">
		<title>Profile</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/header.jsp" />
		<div class="outer-div">
			<c:if test="${not empty loginFailedMessage}">
				<span class="error">${loginFailedMessage}</span>
			</c:if>
			<form:form action="login" method="post" modelAttribute="loginForm">
				<table>
					<tr>
						<td><form:label path="email" style="width:100px">E-mail:</form:label>
						<td><form:input path="email" style="width:350px"/>
						<td><form:errors path="email" cssClass="error" style="margin-left: 10px"/>
					</tr>
					<tr>
						<td><form:label path="password" style="width:100px">Password:</form:label>
						<td><form:password path="password" style="width:350px" id="password"/>
						<td><form:errors path="password" cssClass="error" style="margin-left: 10px"/>
					</tr>
				</table>
				<div style="margin-top: 10px">
					<input type="submit" value="Log In">
					<span>or <i><a href="registration_page">Register</a></i></span>
				</div>
			</form:form>
		</div>
	</body>
</html>