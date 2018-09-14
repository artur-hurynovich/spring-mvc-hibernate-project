package com.hurynovich.prog_lang_tests.validation.custom_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CustomUniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomUnique {
	public String message() default "Specified value is not unique";
	
	public String tableName();
	
	public String fieldName();
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
