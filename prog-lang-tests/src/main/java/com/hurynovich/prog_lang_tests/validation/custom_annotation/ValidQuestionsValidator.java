package com.hurynovich.prog_lang_tests.validation.custom_annotation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hurynovich.prog_lang_tests.entity.Answer;
import com.hurynovich.prog_lang_tests.entity.Question;

public class ValidQuestionsValidator implements ConstraintValidator<ValidQuestions, List<Question>> {
	@Override
	public boolean isValid(List<Question> questions, ConstraintValidatorContext context) {
		boolean valid = true;
		for (Question question : questions) {
			List<Answer> answers = question.getAnswers();
			if (question.getText() == null) {
				valid = false;
			} else if (answers == null) {
				valid = false;
			} else if (answers.isEmpty()){
				valid = false;
			} else {
				for (Answer answer : answers) {
					if (answer.getText() == null || answer.getCorrect() == null) {
						valid = false;
						break;
					}
				}
			}
		};
		return valid;
	}
}
