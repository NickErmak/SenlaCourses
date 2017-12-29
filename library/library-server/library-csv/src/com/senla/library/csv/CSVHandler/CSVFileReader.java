package com.senla.library.csv.CSVHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.senla.library.api.bean.IEntity;
import com.senla.library.api.exception.NonParseableException;

public class CSVFileReader {
	private static Logger logger = Logger.getLogger(CSVFileReader.class);

	@SuppressWarnings("unchecked")
	public static <T extends IEntity> List<T> read(Class<T> entityClass, String filePath) throws NonParseableException {
		CSVInfo entityCSVInfo = new CSVInfo(entityClass, filePath);
		List<T> entities = new ArrayList<>();
		try (FileReader fileReader = new FileReader(entityCSVInfo.getFilePath());
				CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(entityCSVInfo.getCSVParser())
						.build()) {
			Iterator<String[]> csvReaderIterator = csvReader.iterator();
			csvReaderIterator.next();
			while (csvReaderIterator.hasNext()) {
				String[] entityCSV = csvReaderIterator.next();
				Object entity = entityCSVInfo.getConstructor().newInstance(new Object[] {entityCSV});
				entities.add((T) entity);
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}
		return entities;
	}
}
