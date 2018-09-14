package com.hurynovich.prog_lang_tests.validation.form;

import javax.validation.constraints.NotNull;

import com.hurynovich.prog_lang_tests.entity.TestDifficulty;

public class ShowTestsForm {
	@NotNull(message = "Please, choose programming language")
	private Integer langId;
	
	@NotNull(message = "Please, choose difficulty")
	private TestDifficulty difficulty;
	
	public ShowTestsForm() {
		
	}

	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public TestDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(TestDifficulty difficulty) {
		this.difficulty = difficulty;
	}
}
