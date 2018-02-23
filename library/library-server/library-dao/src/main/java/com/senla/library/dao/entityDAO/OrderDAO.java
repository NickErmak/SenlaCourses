package com.senla.library.dao.entityDAO;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import com.senla.library.api.annotation.di.Singleton;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.dao.IOrderDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.entity.Order;

@Singleton
public class OrderDAO extends AbstractDAO<IOrder> implements IOrderDAO{
	private static OrderDAO instance;
	
	public OrderDAO() throws Exception {		
		super();
	}	
	
	public static OrderDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	}

	@Override
	public IOrder getOrder(int id) throws Exception {		
		return super.get(Order.class, id);
	}

	@Override
	public List<IOrder> getOrders() throws Exception {		
		return super.getAll(Order.class);
	}

	@Override
	public List<IOrder> getOrders(SortingCriteria sortingCriteria) throws Exception {		
		return super.getAll(Order.class, sortingCriteria);
	}

	@Override
	public List<IOrder> getOrders(SortingCriteria sortingCriteria, Field date, Date dateFrom) throws Exception {		
		return super.getAll(Order.class, sortingCriteria, date, dateFrom);
	}

	@Override
	public List<IOrder> getOrders(SortingCriteria sortingCriteria, Field date, Date dateFrom, Date dateTo) throws Exception {
		return super.getAll(Order.class, sortingCriteria, date, dateFrom, dateTo);
	} 	
}
