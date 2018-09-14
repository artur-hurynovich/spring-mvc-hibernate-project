package com.hurynovich.prog_lang_tests.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hurynovich.prog_lang_tests.entity.Lang;
import com.hurynovich.prog_lang_tests.entity.Test;
import com.hurynovich.prog_lang_tests.entity.TestDifficulty;
import com.hurynovich.prog_lang_tests.service.ITestService;
import com.hurynovich.prog_lang_tests.util.TestChecker;
import com.hurynovich.prog_lang_tests.validation.form.NewLangForm;
import com.hurynovich.prog_lang_tests.validation.form.NewTestForm;

@Controller
@PropertySources({@PropertySource("classpath:paths.properties"),
	@PropertySource("classpath:messages.properties")})
public class TestController {
	@Autowired
	@Qualifier("testService")
	private ITestService testService;
	
	@Autowired
	@Qualifier("testChecker")
	private TestChecker testChecker;
	
	@Value("${paths.new.lang.page}")
	private String NEW_LANG_PAGE;
	
	@Value("${paths.new.test.page}")
	private String NEW_TEST_PAGE;
	
	@Value("${paths.new.question.page}")
	private String NEW_QUESTION_PAGE;
	
	@Value("${paths.redirect.tests.page}")
	private String REDIRECT_TESTS_PAGE;
	
	@Value("${paths.redirect.result.page}")
	private String REDIRECT_RESULT_PAGE;
	
	@Value("${paths.error.page}")
	private String ERROR_PAGE;
	
	@Value("${messages.no.questions}")
	private String NO_QUESTIONS_MESSAGE;
	
	@RequestMapping("/new_lang")
	public String executeNewLang(@ModelAttribute("newLangForm") 
		@Valid NewLangForm newLangForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return NEW_LANG_PAGE;
		}
		
		Lang lang = new Lang();
		String langName = newLangForm.getName();
		lang.setName(langName);
		testService.addLang(lang);
		
		return REDIRECT_TESTS_PAGE;
	}
	
	@RequestMapping("/new_test")
	public String executeNewTest(HttpServletRequest request, Model model,
		@ModelAttribute("newTestForm") @Valid NewTestForm newTestForm, 
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<Lang> langs = testService.getAllLangs();
			request.setAttribute("langs", langs);
			
			TestDifficulty [] difficulties = TestDifficulty.values();
			request.setAttribute("difficulties", difficulties);
			
			return NEW_TEST_PAGE;
		}
		
		Test test = newTestForm.toTest();
		testService.addTest(test);
		
		return REDIRECT_TESTS_PAGE;
	}
	
	@RequestMapping("/execute_test")
	public String executeTest(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();

		int [] result = testChecker.getResult(params);
		
		return REDIRECT_RESULT_PAGE + "?correct=" + result[0] + "&" + "total=" + result[1];
	}
}
