package com.senla.library.api.dao;

import java.util.List;

import com.senla.library.api.bean.IEntity;

public interface IGenericDAO {
	public <T extends IEntity> List<T> getAll(Class<T> clazz);
	public <T extends IEntity> List<T> getAll(Class<T> clazz, SortingCriteria sortingCriteria);
	public <T extends IEntity> T get(Class<T> clazz, int id);
	public <T extends IEntity> void add(T entity);
	public  <T extends IEntity> void update(T entity);
}
