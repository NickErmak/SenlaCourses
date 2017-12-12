package com.senla.library.annotation.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {
	public enum PropertyType {SimpleProperty, CompositeProperty}
	PropertyType propertyType();	
	int columnNumber() default 0;
	String keyField() default "";		
}
