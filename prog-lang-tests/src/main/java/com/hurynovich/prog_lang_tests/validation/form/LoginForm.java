package com.hurynovich.prog_lang_tests.validation.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class LoginForm {
	@Size(min = 10, max = 45, 
		message = "E-mail length should be between 10 and 45 characters")
	@Email(message = "Please, enter a valid E-mail")
	private String email;
	
	@Size(min = 5, max = 25, 
		message = "Password length should be between 5 and 25 characters")
	private String password;

	public LoginForm() {
		
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}
