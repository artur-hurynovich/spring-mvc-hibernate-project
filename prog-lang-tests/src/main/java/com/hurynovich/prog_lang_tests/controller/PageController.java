package com.hurynovich.prog_lang_tests.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hurynovich.prog_lang_tests.entity.Lang;
import com.hurynovich.prog_lang_tests.entity.Test;
import com.hurynovich.prog_lang_tests.entity.TestDifficulty;
import com.hurynovich.prog_lang_tests.service.ITestService;
import com.hurynovich.prog_lang_tests.validation.form.LoginForm;
import com.hurynovich.prog_lang_tests.validation.form.NewLangForm;
import com.hurynovich.prog_lang_tests.validation.form.NewTestForm;
import com.hurynovich.prog_lang_tests.validation.form.RegistrationForm;
import com.hurynovich.prog_lang_tests.validation.form.ShowTestsForm;

@Controller
@PropertySource("classpath:paths.properties")
public class PageController {
	@Autowired
	@Qualifier("testService")
	private ITestService testService;
	
	@Value("${paths.new.lang.page}")
	private String NEW_LANG_PAGE;
	
	@Value("${paths.new.test.page}")
	private String NEW_TEST_PAGE;
	
	@Value("${paths.test.page}")
	private String TEST_PAGE;
	
	@Value("${paths.tests.page}")
	private String TESTS_PAGE;
	
	@Value("${paths.result.page}")
	private String RESULT_PAGE;
	
	@Value("${paths.registration.page}")
	private String REGISTRATION_PAGE;
	
	@Value("${paths.login.page}")
	private String LOGIN_PAGE;
	
	@Value("${paths.profile.page}")
	private String PROFILE_PAGE;
	
	@Value("${paths.access.denied.page}")
	private String ACCESS_DENIED_PAGE;
	
	@Value("${paths.error.page}")
	private String ERROR_PAGE;
	
	@RequestMapping("/new_lang_page")
	public String executeNewLangPage(Model model) {
		model.addAttribute("newLangForm", new NewLangForm());
		
		return NEW_LANG_PAGE;
	}
	
	@RequestMapping("/new_test_page")
	public String executeNewTestPage(HttpServletRequest request, Model model) {
		model.addAttribute("newTestForm", new NewTestForm());
		
		List<Lang> langs = testService.getAllLangs();
		request.setAttribute("langs", langs);
		
		TestDifficulty [] difficulties = TestDifficulty.values();
		request.setAttribute("difficulties", difficulties);
		
		return NEW_TEST_PAGE;
	}
	
	@RequestMapping("/test_page")
	public String executeTestPage(HttpServletRequest request) {
		String testIdParam = request.getParameter("testId");
		if (testIdParam == null) {
			return ERROR_PAGE;
		}
		
		int testId = Integer.valueOf(testIdParam);
		Test test = testService.getTestById(testId);
		if (test == null) {
			return ERROR_PAGE;
		}
		
		request.setAttribute("test", test);
		
		return TEST_PAGE;
	}
	
	@RequestMapping("/tests_page")
	public String executeTestsPage(HttpServletRequest request, Model model) {
		fillTestsPage(request, model);
		
		model.addAttribute("showTestsForm", new ShowTestsForm());
		
		return TESTS_PAGE;
	}
	
	@RequestMapping("/show_tests_page")
	public String executeShowTestsPage(HttpServletRequest request, Model model,
		@ModelAttribute("showTestsForm") @Valid ShowTestsForm showTestsForm, 
		BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			int langId = showTestsForm.getLangId();
			TestDifficulty difficulty = showTestsForm.getDifficulty();
			List<Test> tests = testService.getTests(langId, difficulty);
			if (tests == null) {
				request.setAttribute("nothingFound", true);
			} else {
				request.setAttribute("tests", tests);
			}
		}
		
		fillTestsPage(request, model);
		
		return TESTS_PAGE;
	}
	
	@RequestMapping("/result_page")
	public String executeResultPage(HttpServletRequest request) {
		String correctParam = request.getParameter("correct");
		String totalParam = request.getParameter("total");
		
		int correct = Integer.valueOf(correctParam);
		int total = Integer.valueOf(totalParam);
		int correctPercentage = (int) (correct * 100 / total);
		
		request.setAttribute("correct", correct);
		request.setAttribute("total", total);
		request.setAttribute("correctPercentage", correctPercentage);
		
		return RESULT_PAGE;
	}
	
	@RequestMapping("/profile_page")
	public String executeProfilePage(HttpServletRequest request, Model model) {
		Object userAttr = request.getSession().getAttribute("user");
		if (userAttr == null) {
			return executeLoginPage(model);
		} else {
			return PROFILE_PAGE;
		}
	}
	
	@RequestMapping("/registration_page")
	public String executeRegistrationPage(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		
		return REGISTRATION_PAGE;
	}
	
	@RequestMapping("/login_page")
	public String executeLoginPage(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		
		return LOGIN_PAGE;
	}
	
	@RequestMapping("/access_denied_page")
	public String executeAccessDeniedPage() {
		return ACCESS_DENIED_PAGE;
	}
	
	private void fillTestsPage(HttpServletRequest request, Model model) {
		List<Lang> langs = testService.getAllLangs();
		request.setAttribute("langs", langs);
		
		TestDifficulty [] difficulties = TestDifficulty.values();
		request.setAttribute("difficulties", difficulties);
	}
}
