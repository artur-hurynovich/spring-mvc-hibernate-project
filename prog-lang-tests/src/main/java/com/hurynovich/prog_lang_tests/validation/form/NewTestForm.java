package com.hurynovich.prog_lang_tests.validation.form;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.hurynovich.prog_lang_tests.entity.Question;
import com.hurynovich.prog_lang_tests.entity.Test;
import com.hurynovich.prog_lang_tests.entity.TestDifficulty;
import com.hurynovich.prog_lang_tests.validation.custom_annotation.ValidQuestions;

public class NewTestForm {
	@NotEmpty(message = "Please, enter test name")
	private String name;

	@NotNull(message = "Please, choose difficulty")
	private TestDifficulty difficulty;
	
	@NotNull(message = "Please, choose programming language")
	private Integer langId;
	
	@ValidQuestions(message = "Please, fill all the fields")
	private List<Question> questions;
	
	public NewTestForm() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TestDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(TestDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public Test toTest() {
		Test test = new Test();
		test.setName(name);
		test.setDifficulty(difficulty);
		test.setLangId(langId);
		Set<Question> questionsSet = new HashSet<>();
		questionsSet.addAll(questions);
		test.setQuestions(questionsSet);
		return test;
	}
}
