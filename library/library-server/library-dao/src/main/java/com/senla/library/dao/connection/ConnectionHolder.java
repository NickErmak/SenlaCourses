package com.senla.library.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.senla.library.api.config.PropertyUnit;
import com.senla.library.config.reader.PropertyReader;

public class ConnectionHolder {

	private static volatile ConnectionHolder instance;
	private volatile Connection dbConnection;

	private ConnectionHolder() throws SQLException {
		connect();
	}

	public static ConnectionHolder getinstance() throws SQLException {
		if (instance == null) {
			synchronized (ConnectionHolder.class) {
				if (instance == null) {
					instance = new ConnectionHolder();
				}
			}
		}
		return instance;
	}

	private void connect() throws SQLException {
		Map<PropertyUnit, String> properties = PropertyReader.getInstance().loadDB();
		String DB_CONNECTION = properties.get(PropertyUnit.DB_CONNECTION);
		String DB_USER = properties.get(PropertyUnit.DB_USER);
		String DB_PASSWORD = properties.get(PropertyUnit.DB_PASSWORD);
		dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
	}

	public Connection getConnection() throws SQLException {
		if (dbConnection == null) {
			connect();
		}
		return dbConnection;
	}

	public void closeConnection() throws SQLException {
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
}
