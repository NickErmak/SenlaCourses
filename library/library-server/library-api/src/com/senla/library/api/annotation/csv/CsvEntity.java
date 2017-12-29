package com.senla.library.api.annotation.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvEntity {
	String fileName() default "entity";
	char valuesSeparator() default ';';
	String entityId() default "id";
}
