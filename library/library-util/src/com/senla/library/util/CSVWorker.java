package com.senla.library.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVParser;

public class CSVWorker {
	private static final char DEFAULT_SEPARATOR = ';';
	private static final char DEFAULT_QUOTE_CHARACTER = '"';
	private static final char DEFAULT_ESCAPE_CHARACTER = '\\';
	private static final String DEFAULT_LINE_END = "\n";
	private static Logger logger = Logger.getLogger(CSVWorker.class);
	private static ICSVParser csvParser;

	static {
		csvParser = new CSVParserBuilder().withSeparator(DEFAULT_SEPARATOR).withQuoteChar(DEFAULT_QUOTE_CHARACTER)
				.withEscapeChar(DEFAULT_ESCAPE_CHARACTER).build();
	}

	public static void saveCSV(String filePath, List<String[]> entities) {
		try (FileWriter writer = new FileWriter(filePath);
				CSVWriter csvWriter = new CSVWriter(writer, DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER,
						DEFAULT_ESCAPE_CHARACTER, DEFAULT_LINE_END);) {
			csvWriter.writeAll(entities);
			csvWriter.flush();
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public static List<String[]> loadCSV(String filePath) {
		List<String[]> entities = null;
		try (FileReader fileReader = new FileReader(filePath);
				CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build()) {
			entities = csvReader.readAll();
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return entities;
	}
}
