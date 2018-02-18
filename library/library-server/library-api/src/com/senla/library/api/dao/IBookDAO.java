package com.senla.library.api.dao;

import java.util.List;

import com.senla.library.api.bean.IBook;

public interface IBookDAO extends IGenericDAO{
	
	public List<?> getAll();
	public List<?> getAll(SortingCriteria sortingCriteria);
	public IBook get(int id);
}
