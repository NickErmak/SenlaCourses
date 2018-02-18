package com.senla.library.api.dao;

import java.util.List;

import com.senla.library.api.bean.IOrder;

public interface IOrderDAO extends IGenericDAO{

	public List<?> getAll();
	public List<?> getAll(SortingCriteria sortingCriteria);
	public IOrder get(int id);
}
