<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" 
		href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
		<link rel="stylesheet" href="resources/css/main.css" type="text/css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
		<title>New Test</title>
		<style type="text/css">
			select {
				height: 30px;
				line-height: 30px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="/WEB-INF/views/header.jsp" />
		<div class="outer-div">
			<form:form action="new_test" method="post" modelAttribute="newTestForm">
				<table>
					<tr>
						<td><form:label path="name" style="width:300px">Enter Test Name:</form:label>
						<td><form:input path="name" style="width:400px" size="250"/>
						<td><form:errors path="name" cssClass="error" style="margin-left: 10px"/>
					</tr>
					<tr>
						<td><form:label path="langId" style="width:300px">Choose Programming Language:</form:label>
						<td><form:select path="langId" style="width:400px">
							<form:option value="" />
							<form:options items="${langs}" itemValue="id" itemLabel="name"/>
						</form:select>
						<td><form:errors path="langId" cssClass="error" style="margin-left: 10px"/>
					</tr>
					<tr>
						<td><form:label path="difficulty" style="width:300px">Choose Difficulty:</form:label>
						<td><form:select path="difficulty" style="width:400px">
							<form:option value="" />
							<form:options items="${difficulties}" />
						</form:select>
						<td><form:errors path="difficulty" cssClass="error" style="margin-left: 10px"/>
					</tr>
				</table>
				<div class="questionDiv">
					<div class="inputQuestionDiv" id="inputQuestionDiv0">
						<br>
						<span class="questionSpan"><b>Question #1:</b></span>
						<form:errors path="questions" cssClass="error" style="margin-left: 10px"
						class="textErrors"/>
						<table>
							<tr><td><span>Question Text:</span>
							<tr>
								<td><form:input path="questions[0].text" style="width:550px" size="250" 
								class="textInput"/>
							</tr>
						</table>
						<table>
							<tr>
								<td style="width:550px">Answer Text:
								<td style="width:150px">Correct Answer:
							</tr>
						</table>
						<div class="answerDiv" id="answerDiv0">
							<div class="inputAnswerDiv" id="inputAnswerDiv0">
								<table>
									<tr>
										<td><form:input path="questions[0].answers[0].text" style="width:550px" 
										size="250"/>
										<td><form:select path="questions[0].answers[0].correct" 
										style="width:150px">
											<form:option value=""></form:option>
											<form:option value="true">true</form:option>
											<form:option value="false">false</form:option>
										</form:select>
									</tr>
								</table>
							</div>
							<div class="inputAnswerDiv" id="inputAnswerDiv1">
								<table>
									<tr>
										<td><form:input path="questions[0].answers[1].text" style="width:550px" 
										size="250"/>
										<td><form:select path="questions[0].answers[1].correct" style="width:150px">
											<form:option value=""></form:option>
											<form:option value="true">true</form:option>
											<form:option value="false">false</form:option>
										</form:select>
									</tr>
								</table>
							</div>
						</div>
						<button type="button" class="addAnswer">Add Answer</button>
						<button type="button" class="removeAnswer">Remove Answer</button><br>
					</div>
				</div>
				<br>
				<button type="button" class="addQuestion">Add Question</button>
				<button type="button" class="removeQuestion">Remove Question</button>
				<input type="submit" value="Save Test">
			</form:form>
		</div>
		<div class="questionModal">
			<span>Test should have at least one question</span>
		</div>
		<div class="answerModal">
			<span>Question should have at least two answers</span>
		</div>
		<script type="text/javascript">
			$("div.questionModal").dialog({
	            autoOpen: false,
	            dialogClass: "modalStyle",
	            width: "auto",
	            modal: true,
	            buttons: {
	            	Ok: function() {
	            		$(this).dialog("close");
	            	}
	            }
	        });
			
			$("div.answerModal").dialog({
	            autoOpen: false,
	            dialogClass: "modalStyle",
	            width: "auto",
	            modal: true,
	            buttons: {
	            	Ok: function() {
	            		$(this).dialog("close");
	            	}
	            }
	        });
		
			$("button.addAnswer").on("click", addAnswer);
			
			$("button.removeAnswer").on("click", removeAnswer);
			
			$("button.addQuestion").on("click", addQuestion);
			
			$("button.removeQuestion").on("click", removeQuestion);
			
			function addQuestion() {
				var newQuestionIndex = $("div.inputQuestionDiv").length;
				
				var cloneIndex = newQuestionIndex - 1;
				
				var $newQuestionDiv = $("#inputQuestionDiv" + cloneIndex).clone(true).appendTo("div.questionDiv");
				$newQuestionDiv.attr("id", "inputQuestionDiv" + newQuestionIndex);
				$newQuestionDiv.find("input").val("");
				
				$newQuestionDiv.find("span.questionSpan").html("<b>Question #" + (newQuestionIndex + 1) + ":</b>");
				
				var $questionTextInput = $newQuestionDiv.find("input.textInput");
				$questionTextInput.attr("id", "questions" + newQuestionIndex + ".text");
				$questionTextInput.attr("name", "questions[" + newQuestionIndex + "].text");
				
				var $answerDiv = $newQuestionDiv.find("div.answerDiv");
				$answerDiv.attr("id", "answerDiv" + newQuestionIndex);
				
				$answerDiv.find("input").each(function(){
					var newId = $(this).attr("id").replace(/questions\d*/, "questions" + newQuestionIndex);
					$(this).attr("id", newId);
				});
				$answerDiv.find("input").each(function(){
					var newName = $(this).attr("name").replace(/questions\[\d*/, "questions[" + newQuestionIndex);
					$(this).attr("name", newName);
				});
				$answerDiv.find("select").each(function(){
					var newId = $(this).attr("id").replace(/questions\d*/, "questions" + newQuestionIndex);
					$(this).attr("id", newId);
				});
				$answerDiv.find("select").each(function(){
					var newName = $(this).attr("name").replace(/questions\[\d*/, "questions[" + newQuestionIndex);
					$(this).attr("name", newName);
				});
				/* ERRORS */
			}
			
			function removeQuestion() {
				var questionsCount = $("div.inputQuestionDiv").length;
				
				if (questionsCount == 1) {
					$(".ui-dialog-titlebar").hide();
					$("div.questionModal").dialog("open");
				} else {
					$("#inputQuestionDiv" + (questionsCount - 1)).remove();
				} 
			}
			
			function addAnswer() {
				var $answerDiv = $(this).parent().find("div.answerDiv");
				
				var questionIndex = $answerDiv.attr("id").replace(/\D*/, "");
				
				var newAnswerIndex = $answerDiv.find("div.inputAnswerDiv").length;
				
				var cloneIndex = newAnswerIndex - 1;
				
				var $inputAnswerDiv = $answerDiv.find("#inputAnswerDiv" + cloneIndex).clone().appendTo($answerDiv);
				$inputAnswerDiv.attr("id", "inputAnswerDiv" + newAnswerIndex);
				
				var $answerInput = $inputAnswerDiv.find("input");
				$answerInput.attr("id", "questions" + questionIndex + ".answers" + newAnswerIndex + ".text");
				$answerInput.attr("name", "questions[" + questionIndex + "].answers[" + newAnswerIndex + "].text");
				$answerInput.val("");
				
				var $answerSelect =	$inputAnswerDiv.find("select");
				$answerSelect.attr("id", "questions" + questionIndex + ".answers" + newAnswerIndex + ".correct");
				$answerSelect.attr("name", "questions[" + questionIndex + "].answers[" + newAnswerIndex + "].correct");
				$answerSelect.prop("selectedIndex", 0);
			}
			
			function removeAnswer() {
				var $answerDiv = $(this).parent().find("div.answerDiv");
				
				var answersCount = $answerDiv.find("div.inputAnswerDiv").length;
				
				if (answersCount == 2) {
					$(".ui-dialog-titlebar").hide();
					$("div.answerModal").dialog("open");
				} else {
					$answerDiv.find("div.inputAnswerDiv").last().remove();
				} 
			}
		</script>
	</body>
</html>