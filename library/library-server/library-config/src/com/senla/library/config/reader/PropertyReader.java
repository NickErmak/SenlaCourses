package com.senla.library.config.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.senla.library.api.config.IPropertyReader;
import com.senla.library.api.config.PropertyUnit;

public class PropertyReader implements IPropertyReader {
	private static final String PATH_TO_TOTAL_PROPERTIES = "/config.properties";
	private static final String PATH_TO_DB_PROPERTIES = "/configDB.properties";
	private static PropertyReader instance;
	private static Logger logger = Logger.getLogger(PropertyReader.class);
	private Properties properties;

	private PropertyReader() {
		properties = new Properties();
	}

	public static PropertyReader getInstance() {
		if (instance == null) {
			instance = new PropertyReader();
		}
		return instance;
	}

	private Map<PropertyUnit, String> load(String path) {
		Map<PropertyUnit, String> propertiesMap = new HashMap<>();
		try (InputStream inputStream = PropertyReader.class.getResourceAsStream(path)) {
			properties.load(inputStream);
			for (PropertyUnit propertyType : PropertyUnit.values()) {
				propertiesMap.put(propertyType, properties.getProperty(propertyType.toString()));
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return propertiesMap;
	}

	public Map<PropertyUnit, String> loadDB() {
		return load(PATH_TO_DB_PROPERTIES);
	}

	public Map<PropertyUnit, String> loadTotal() {
		return load(PATH_TO_TOTAL_PROPERTIES);
	}
}
