<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<c:set var="user" value="${sessionScope.user}" />
			<c:choose>
				<c:when test="${user.role eq 'ADMIN'}">
					<table>
						<tr><td><a href="new_lang_page">New Programming Language...</a>
						<tr><td><a href="new_test_page">New Test...</a>
					</table>
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
			<form action="logout">
				<input type="submit" style="margin-top: 10px" value="Log Out">
			</form>
		</div>
	</body>
</html>