package com.senla.library.csv.CSVHandler;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.CSVWriter;
import com.senla.library.api.annotation.csv.CsvProperty;
import com.senla.library.api.bean.IEntity;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.util.DateConverter;

public class CSVFileWriter {
	private static Logger logger = Logger.getLogger(CSVFileWriter.class);
	private static final char DEFAULT_QUOTE_CHARACTER = '"';
	private static final char DEFAULT_ESCAPE_CHARACTER = '\\';
	private static final String DEFAULT_LINE_END = "\n";

	private static String simpleParse(Object object) {
		if (object == null) {
			return "null";
		} else {
			return (object instanceof Date) ? DateConverter.dateToString((Date) object) : object.toString();
		}
	}

	private static String compositeParse(Object field, String keyField, char separator)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		StringBuilder parsedField = new StringBuilder();
		if (field instanceof List<?>) {
			Iterator<?> iterator = ((List<?>) field).iterator();
			while (iterator.hasNext()) {
				Object item = iterator.next();
				Field itemKeyField = item.getClass().getDeclaredField(keyField);
				itemKeyField.setAccessible(true);
				parsedField.append(itemKeyField.get(item)).append(separator);
			}
		}
		return parsedField.toString();
	}

	public static <T extends IEntity> void write(List<T> entities, String filePath) throws NonParseableException {
		CSVInfo csvInfo = new CSVInfo(entities.get(0).getClass(), filePath);
		try (FileWriter writer = new FileWriter(csvInfo.getFilePath());
				CSVWriter csvWriter = new CSVWriter(writer, csvInfo.getSeparator(), DEFAULT_QUOTE_CHARACTER,
						DEFAULT_ESCAPE_CHARACTER, DEFAULT_LINE_END);) {
			csvWriter.writeNext(csvInfo.getHeader());
			Iterator<T> iteratorEntities = entities.iterator();
			while (iteratorEntities.hasNext()) {
				T entity = iteratorEntities.next();
				List<String> entityLine = new ArrayList<>();
				entityLine.add(csvInfo.getId().get(entity).toString());
				Iterator<Field> iteratorFields = csvInfo.getFields().iterator();
				while (iteratorFields.hasNext()) {
					Field field = iteratorFields.next();
					switch (field.getAnnotation(CsvProperty.class).propertyType()) {
					case SimpleProperty:
						String s = simpleParse(field.get(entity));
						entityLine.add(s);
						break;
					case CompositeProperty:
						String keyField = field.getAnnotation(CsvProperty.class).keyField();
						entityLine.add(compositeParse(field.get(entity), keyField, csvInfo.getSeparator()));
						break;
					}
				}
				csvWriter.writeNext(entityLine.toArray(new String[0]));
				csvWriter.flush();
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
