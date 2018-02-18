package com.senla.library.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.senla.library.api.config.PropertyUnit;
import com.senla.library.config.reader.PropertyReader;

public class ConnectionHolder {

	private static Logger logger = Logger.getLogger(ConnectionHolder.class);
	private static final String DB_CONNECTION;
	private static final String DB_USER;
	private static final String DB_PASSWORD;		
	private static volatile Connection instance;
	
	static {
		Map<PropertyUnit, String> properties = PropertyReader.getInstance().loadDB();
		DB_CONNECTION = properties.get(PropertyUnit.DB_CONNECTION);
		DB_USER = properties.get(PropertyUnit.DB_USER);
		DB_PASSWORD = properties.get(PropertyUnit.DB_PASSWORD);
	}

	public static Connection getConnection() {
		if (instance == null) {
			synchronized (ConnectionHolder.class) {
				if (instance == null) {
					try {
						instance = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
					} catch (SQLException e) {
						logger.error(e);
					}
				}
			}
		}
		return instance;
	}
	
	public static void closeConnection() {
		try {
			instance.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}
