package com.hurynovich.prog_lang_tests.dao;

import java.util.List;

import com.hurynovich.prog_lang_tests.entity.Lang;
import com.hurynovich.prog_lang_tests.entity.Test;
import com.hurynovich.prog_lang_tests.entity.TestDifficulty;

public interface ITestDAO {
	// Create methods
	void addLang(Lang lang);
	
	void addTest(Test test);
	
	// Read methods
	List<Lang> getAllLangs();

	Test getTestById(int testId);
	
	List<Test> getTests(int langId, TestDifficulty difficulty);
}
