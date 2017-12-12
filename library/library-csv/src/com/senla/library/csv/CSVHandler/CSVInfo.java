package com.senla.library.csv.CSVHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.senla.library.annotation.csv.CsvConstructor;
import com.senla.library.annotation.csv.CsvEntity;
import com.senla.library.annotation.csv.CsvProperty;
import com.senla.library.api.bean.IEntity;
import com.senla.library.api.exception.NonParseableException;

public class CSVInfo {
	private static Logger logger = Logger.getLogger(CSVInfo.class);
	private final Class<? extends IEntity> entityClass;
	private String filePath;
	private char separator;
	private Field id;
	private Map<Integer, Field> fields;

	public CSVInfo(Class<? extends IEntity> entityClass, String filePath) throws NonParseableException {
		this.entityClass = entityClass;
		this.filePath = filePath;
		fields = new TreeMap<>();
		if (entityClass.isAnnotationPresent(CsvEntity.class)) {
			loadClassContext(entityClass.getAnnotation(CsvEntity.class));
			loadFieldContext(entityClass.getDeclaredFields());
		} else
			throw new NonParseableException();
	}

	private void loadClassContext(CsvEntity csvEntity) {
		try {
			filePath += csvEntity.fileName();
			id = entityClass.getDeclaredField(csvEntity.entityId());
			separator = csvEntity.valuesSeparator();
		} catch (NoSuchFieldException e) {
			logger.error(e);
		}		
	}

	private void loadFieldContext(Field[] fields) {
		for (Field field : fields) {			
			if (field.isAnnotationPresent(CsvProperty.class)) {
				field.setAccessible(true);
				int columnNumber = field.getAnnotation(CsvProperty.class).columnNumber();
				this.fields.put(columnNumber, field);
			}
		}
	}		
	
	public Constructor<?> getConstructor() {
		Constructor<?> csvConstructor = null;
		for (Constructor<?> constructor : entityClass.getConstructors()) {
			if (constructor.isAnnotationPresent(CsvConstructor.class)) {
				csvConstructor = constructor;
			}
		}
		return csvConstructor;
	}		

	public CSVParser getCSVParser() {
		return new CSVParserBuilder().withSeparator(separator).build();
	}		
	
	public String[] getHeader() {
		List<String> header = new ArrayList<>();
		header.add(id.getName());
		Iterator<Field> iterator = getFields().iterator();
		while(iterator.hasNext()) {
			header.add(iterator.next().getName());			
		}
		return header.toArray(new String[0]);
	}
	
	public Collection<Field> getFields() {
		return fields.values();
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public char getSeparator() {
		return separator;
	}
	
	public Field getId() {
		id.setAccessible(true);
		return id;
	}
}
