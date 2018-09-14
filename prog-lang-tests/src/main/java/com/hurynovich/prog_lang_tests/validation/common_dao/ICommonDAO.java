package com.hurynovich.prog_lang_tests.validation.common_dao;

public interface ICommonDAO {
	boolean valueExists(String tableName, String fieldName, String value);
}
