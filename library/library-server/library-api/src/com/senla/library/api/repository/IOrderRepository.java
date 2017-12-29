package com.senla.library.api.repository;

import java.util.List;

import com.senla.library.api.bean.IOrder;
import com.senla.library.api.exception.NoSuchIdException;

public interface IOrderRepository {

	void addOrder(IOrder order);
	IOrder getOrder(int id) throws NoSuchIdException;
	void refreshOrder(IOrder deprecatedOrder, IOrder refreshedOrder);
	List<IOrder> getOrders();
	void readData(String filePath);
	void saveData(String filePath);
}
