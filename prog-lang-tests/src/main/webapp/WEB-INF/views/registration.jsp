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
			<form:form action="registration" method="post" modelAttribute="registrationForm">
				<table>
					<tr>
						<td><form:label path="name" style="width:150px">Name:</form:label>
						<td><form:input path="name" style="width:350px"/>
						<td><form:errors path="name" cssClass="error" style="margin-left: 10px"/>
					</tr>
					<tr>
						<td><form:label path="email" style="width:150px">E-mail:</form:label>
						<td><form:input path="email" style="width:350px"/>
						<td><form:errors path="email" cssClass="error" style="margin-left: 10px"/>
					</tr>
					<tr>
						<td><form:label path="password" style="width:150px">Password:</form:label>
						<td><form:password path="password" style="width:350px"/>
						<td><form:errors path="password" cssClass="error" style="margin-left: 10px"/>
					</tr>
				</table>
				<input type="submit" value="Registration" style="margin-top: 10px">
			</form:form>
		</div>
	</body>
</html>