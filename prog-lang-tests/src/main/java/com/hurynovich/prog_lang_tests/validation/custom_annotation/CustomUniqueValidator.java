package com.hurynovich.prog_lang_tests.validation.custom_annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hurynovich.prog_lang_tests.validation.common_dao.ICommonDAO;

public class CustomUniqueValidator implements ConstraintValidator<CustomUnique, String> {
	@Autowired
	@Qualifier("commonDao")
	private ICommonDAO commonDao;
	
	private String tableName;
	private String fieldName;
	
	@Override
	public void initialize(CustomUnique constraint) {
		tableName = constraint.tableName();
		fieldName = constraint.fieldName();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !commonDao.valueExists(tableName, fieldName, value);
	}
}
