package com.hurynovich.prog_lang_tests.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hurynovich.prog_lang_tests.dao.ITestDAO;
import com.hurynovich.prog_lang_tests.entity.Lang;
import com.hurynovich.prog_lang_tests.entity.Test;
import com.hurynovich.prog_lang_tests.entity.TestDifficulty;

@Service("testService")
public class TestService implements ITestService {
	@Autowired
	@Qualifier("testDao")
	private ITestDAO testDao;
	
	// Create methods
	@Override
	public void addLang(Lang lang) {
		testDao.addLang(lang);
	}
	
	@Override
	public void addTest(Test test) {
		testDao.addTest(test);
	}
	
	// Read methods
	@Override
	public List<Lang> getAllLangs() {
		return testDao.getAllLangs();
	}

	@Override
	public Test getTestById(int testId) {
		return testDao.getTestById(testId);
	}
	
	@Override
	public List<Test> getTests(int langId, TestDifficulty difficulty) {
		return testDao.getTests(langId, difficulty);
	}
}
