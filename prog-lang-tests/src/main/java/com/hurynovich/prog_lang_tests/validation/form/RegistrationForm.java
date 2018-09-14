package com.hurynovich.prog_lang_tests.validation.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.hurynovich.prog_lang_tests.entity.User;
import com.hurynovich.prog_lang_tests.entity.UserRole;
import com.hurynovich.prog_lang_tests.validation.custom_annotation.CustomUnique;

public class RegistrationForm {
	@Size(min = 5, max = 45, 
		message = "Name length should be between 5 and 45 characters")
	private String name;
	
	@Size(min = 10, max = 45, 
		message = "E-mail length should be between 10 and 45 characters")
	@Email(message = "Please, enter a valid E-mail")
	@CustomUnique(message = "User with specified E-mail is already registered",
		tableName = "users", fieldName = "user_email")
	private String email;
	
	@Size(min = 5, max = 25, 
		message = "Password length should be between 5 and 25 characters")
	private String password;
	
	public RegistrationForm() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
	
	public User toUser() {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(UserRole.USER);
		return user;
	}
}
