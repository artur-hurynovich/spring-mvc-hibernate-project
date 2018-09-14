package com.hurynovich.prog_lang_tests.dao;

import com.hurynovich.prog_lang_tests.entity.User;

public interface IUserDAO {
	// Create methods
	void addUser(User user);
	
	// Read methods
	User getUserByEmail(String email);
}
