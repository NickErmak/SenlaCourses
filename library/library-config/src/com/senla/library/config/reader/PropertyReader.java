package com.senla.library.config.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.senla.library.api.config.IPropertyReader;
import com.senla.library.api.config.PropertyType;

public class PropertyReader implements IPropertyReader {
	private static final String PATH_TO_PROPERTIES = "/config.properties";
	private static IPropertyReader instance;
	private static Logger logger = Logger.getLogger(PropertyReader.class);
	private Properties properties;

	private PropertyReader() {
		properties = new Properties();
	}

	public static IPropertyReader getInstance() {
		if (instance == null) {
			instance = new PropertyReader();
		}
		return instance;
	}

	@Override
	public Map<PropertyType, String> load() {
		Map<PropertyType, String> propertiesMap = new HashMap<>();
		try (InputStream inputStream = PropertyReader.class.getResourceAsStream(PATH_TO_PROPERTIES)) {
			properties.load(inputStream);
			for (PropertyType propertyType : PropertyType.values()) {
				propertiesMap.put(propertyType, properties.getProperty(propertyType.toString()));
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return propertiesMap;
	}
}
