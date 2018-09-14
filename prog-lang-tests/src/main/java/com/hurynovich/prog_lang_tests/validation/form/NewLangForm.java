package com.hurynovich.prog_lang_tests.validation.form;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import com.hurynovich.prog_lang_tests.validation.custom_annotation.CustomUnique;

public class NewLangForm {
	@Column(name = "lang_name")
	@Size(min = 1, max = 15, 
		message = "Programming language name length should be between 10 and 45 characters")
	@CustomUnique(message = "Programming language with specified name already exists",
		tableName = "langs", fieldName = "lang_name")
	private String name;

	public NewLangForm() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
