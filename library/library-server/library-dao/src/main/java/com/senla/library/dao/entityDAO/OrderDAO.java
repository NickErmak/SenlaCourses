package com.senla.library.dao.entityDAO;

import java.util.List;

import com.senla.library.api.annotation.di.Singleton;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.dao.IOrderDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.entity.Order;

@Singleton
public class OrderDAO extends GenericDAO implements IOrderDAO{
	private static IOrderDAO instance;
	
	public static IOrderDAO getInstance() {
		if (instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	} 
	
	public IOrder get(int id) {
		return get(Order.class, id);
	}
	
	public List<Order> getAll(SortingCriteria sortingCriteria) {
		return getAll(Order.class, sortingCriteria);
	}
	
	public List<Order> getAll() {
		return getAll(Order.class);
	}
}
