package com.hurynovich.prog_lang_tests.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hurynovich.prog_lang_tests.dao.ITestDAO;
import com.hurynovich.prog_lang_tests.entity.Answer;
import com.hurynovich.prog_lang_tests.entity.Question;
import com.hurynovich.prog_lang_tests.entity.Test;

@Service("testChecker")
public class TestChecker {
	@Autowired
	@Qualifier("testDao")
	private ITestDAO testDao;
	
	public int[] getResult(Map<String, String[]> params) {
		String testIdParam = params.get("testId")[0];
		int testId = Integer.valueOf(testIdParam);
		Test test = testDao.getTestById(testId);
		int totalQuestions = test.getQuestions().size();
		
		Set<Question> questions = test.getQuestions();
		int correctQuestions = 0;
		for (Question question : questions) {
			int questionId = question.getId();
			Set<Integer> correctAnswersIds = new HashSet<>();
			for (Answer answer : question.getAnswers()) {
				if (answer.getCorrect()) {
					correctAnswersIds.add(answer.getId());
				}
			}
			
			String[] checkAnswers = params.get("q" + questionId);
			Set<Integer> checkAnswersIds = new HashSet<>();
			for (String checkAnswer : checkAnswers) {
				int checkAnswerId = Integer.valueOf(checkAnswer.replaceAll("\\D", ""));
				checkAnswersIds.add(checkAnswerId);
			}
			
			if (correctAnswersIds.equals(checkAnswersIds)) {
				correctQuestions++;
			}
		}
		
		return new int[] {correctQuestions, totalQuestions};
	}
}
