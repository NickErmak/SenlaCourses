package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.bean.IOrder;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.util.CollectionHandler;
import com.senla.library.util.FileWorker;

public class OrderRepository {
	private static OrderRepository instance;
	private List<IOrder> orders;

	private OrderRepository() {
		orders = new ArrayList<>();
	}

	public static OrderRepository getInstance() {
		if (instance == null) {
			instance = new OrderRepository();
		}
		return instance;
	}

	public void addOrder(IOrder order) {
		orders.add(order);
	}

	public IOrder getOrder(int id) throws NoSuchIdException {
		return CollectionHandler.getElementById(id, orders);
	}

	public List<IOrder> getOrders() {
		return orders;
	}

	@SuppressWarnings("unchecked")
	public void readData(String filePath) {
		Object fileData = FileWorker.read(filePath);
		if (fileData != null) {
			orders = (List<IOrder>) fileData;
		}
	}

	public void saveData(String filePath) {
		FileWorker.save(orders, filePath);
	}
}
