package com.hurynovich.prog_lang_tests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hurynovich.prog_lang_tests.dao.IUserDAO;
import com.hurynovich.prog_lang_tests.entity.User;

@Service("userService")
public class UserService implements IUserService {
	@Autowired
	@Qualifier("userDao")
	private IUserDAO userDao;
	
	// Create methods
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	// Read methods
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		User user = userDao.getUserByEmail(email);
		if (user == null) {
			return null;
		} else if (user.getPassword().equals(password)) {
			user.setPassword(null);
			return user;
		} else {
			return null;
		}
	}
}
