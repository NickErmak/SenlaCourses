package com.senla.library.api.dao;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IOrder;

public interface IOrderDAO extends IGenericDAO<IOrder> {

	IOrder getOrder(int id) throws Exception;
	List<IOrder> getOrders() throws Exception;
	List<IOrder> getOrders(SortingCriteria sortingCriteria) throws Exception;
	List<IOrder> getOrders(SortingCriteria sortingCriteria, Field date, Date dateFrom) throws Exception;	
	List<IOrder> getOrders(SortingCriteria sortingCriteria, Field date, Date dateFrom, Date dateTo) throws Exception;	
}
