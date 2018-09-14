package com.hurynovich.prog_lang_tests.service;

import com.hurynovich.prog_lang_tests.entity.User;

public interface IUserService {
	// Create methods
	void addUser(User user);
	
	// Read methods
	User getUserByEmailAndPassword(String email, String password);
}
