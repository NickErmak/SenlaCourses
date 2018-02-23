package com.senla.library.api.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IEntity;

public  interface  GenericDAO<T extends IEntity> {
	
	List<T> getAll(Class<? extends T> clazz) throws Exception;
	List<T> getAll(Class<? extends T> clazz, SortingCriteria sortingCriteria) throws Exception;
	List<T> getAll(Class<? extends T> clazz, SortingCriteria sortingCriteria, Field date, Date dateFrom)
			throws Exception;
	List<T> getAll(Class<? extends T> clazz, SortingCriteria sortingCriteria, Field date, Date dateFrom, Date dateTo)
			throws Exception;	
	T get(Class<? extends T> clazz, int id) throws Exception;
	void add(T entity) throws Exception;
	void update(T entity) throws Exception;
	Connection getConnection() throws SQLException;
	void exit() throws Exception;	
}
