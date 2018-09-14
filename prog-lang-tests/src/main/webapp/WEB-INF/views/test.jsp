<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="resources/css/main.css" type="text/css">
		<title>Test</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/header.jsp" />
		<div class="outer-div">
			<form action="execute_test" method="post">
				<input type="hidden" name="testId" value="${test.id}">
				<span><b>${test.name}</b></span><br><br>
				<c:forEach var="question" items="${test.questions}" varStatus="loop">
					<span><b>${loop.index + 1}. ${question.text}</b></span><br>
					<c:set var="correct" value="0" />
					<c:forEach var="answer" items="${question.answers}">
						<c:if test="${answer.correct eq true}">
							<c:set var="correct" value="${correct + 1}" />
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${correct eq 1}">
							<c:forEach var="answer" items="${question.answers}">
								<input type="radio" name="q${question.id}" value="a${answer.id}"
								style="margin-left: 20px">
								<label for="q${question.id}">${answer.text} - ${answer.correct}</label><br>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="answer" items="${question.answers}">
								<input type="checkbox" name="q${question.id}" value="a${answer.id}"
								style="margin-left: 20px">
								<label for="q${question.id}">${answer.text} - ${answer.correct}</label><br>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:forEach><br>
				<input type="submit" value="Done">
			</form>
		</div>
	</body>
</html>