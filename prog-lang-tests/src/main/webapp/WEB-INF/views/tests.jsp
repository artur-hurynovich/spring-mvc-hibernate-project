<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="resources/css/main.css" type="text/css">
		<style type="text/css">
			select {
				height: 30px;
				line-height: 30px;
			}
		</style>
		<title>Programming Languages Tests</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/header.jsp" />
		<div class="outer-div">
			<form:form action="show_tests_page" method="post" modelAttribute="showTestsForm">
				<table>
					<tr>
						<td><form:label path="langId" style="width:300px">Choose Programming Language:</form:label>
						<td><form:select path="langId" style="width:350px">
							<form:option value="" />
							<form:options items="${langs}" itemValue="id" itemLabel="name"/>
						</form:select>
						<td><form:errors path="langId" cssClass="error" style="margin-left: 10px"/>
					</tr>
					<tr>
						<td><form:label path="difficulty" style="width:300px">
							Choose Difficulty:</form:label>
						<td><form:select path="difficulty" style="width:350px">
							<form:option value="" />
							<form:options items="${difficulties}" />
						</form:select>
						<td><form:errors path="difficulty" cssClass="error" style="margin-left: 10px"/>
					</tr>
				</table>
				<input type="submit" value="Show Tests" style="margin-top: 10px">
			</form:form><br>
			<c:if test="${nothingFound}">
				<span class="error">No tests were found by your search criteria</span>
			</c:if>
			<c:if test="${not empty tests}">
				<span><b>Tests Found:</b></span><br>
				<form action="test_page" method="post">
					<table>
						<c:forEach var="test" items="${tests}">
							<tr>
								<td><div style="min-width: 200px"><span>${test.name}</span></div>
								<td><button type="submit" name="testId" value="${test.id}">Start Test</button>
							</tr>
						</c:forEach>
					</table>
				</form>
			</c:if>
		</div>
	</body>
</html>