package com.hurynovich.prog_lang_tests.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hurynovich.prog_lang_tests.entity.User;
import com.hurynovich.prog_lang_tests.service.IUserService;
import com.hurynovich.prog_lang_tests.validation.form.LoginForm;
import com.hurynovich.prog_lang_tests.validation.form.RegistrationForm;

@Controller
@PropertySources({@PropertySource("classpath:paths.properties"),
	@PropertySource("classpath:messages.properties")})
public class UserController {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Value("${paths.redirect.tests.page}")
	private String REDIRECT_TESTS_PAGE;
	
	@Value("${paths.registration.page}")
	private String REGISTRATION_PAGE;
	
	@Value("${paths.login.page}")
	private String LOGIN_PAGE;
	
	@Value("${paths.redirect.login.page}")
	private String REDIRECT_LOGIN_PAGE;
	
	@Value("${paths.redirect.profile.page}")
	private String REDIRECT_PROFILE_PAGE;
	
	@Value("${messages.login.failed}")
	private String LOGIN_FAILED_MESSAGE;
	
	@RequestMapping("/registration")
	public String executeRegistration(@ModelAttribute("registrationForm") 
		@Valid RegistrationForm registrationForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return REGISTRATION_PAGE;
		}

		User user = registrationForm.toUser();
		userService.addUser(user);
		return REDIRECT_LOGIN_PAGE;
	}
	
	@RequestMapping("/login")
	public String executeLogin(@ModelAttribute("loginForm") 
		@Valid LoginForm loginForm, BindingResult bindingResult, 
		HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return LOGIN_PAGE;
		} 
		
		String email = loginForm.getEmail();
		String password = loginForm.getPassword();
		User user = userService.getUserByEmailAndPassword(email, password);
		if (user == null) {
			request.setAttribute("loginFailedMessage", LOGIN_FAILED_MESSAGE);
			return LOGIN_PAGE;
		}
		
		request.getSession().setAttribute("user", user);
		return REDIRECT_PROFILE_PAGE;
		
	}
	
	@RequestMapping("/logout")
	public String executeLogout(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return REDIRECT_TESTS_PAGE;
	}
}
