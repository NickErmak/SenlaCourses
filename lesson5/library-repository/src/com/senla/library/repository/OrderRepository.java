package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.bean.IOrder;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IOrderRepository;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.FileWorker;

public class OrderRepository implements IOrderRepository {

	private static final String FILE_PATH = "data/order.ser";
	private static OrderRepository instance;
	private List<IOrder> orders;

	public static OrderRepository getInstance() {
		if (instance == null)
			instance = new OrderRepository();
		return instance;
	}

	private OrderRepository() {
		orders = new ArrayList<>();
		readData();
	}

	public void addOrder(IOrder order) {
		orders.add(order);
	}

	public IOrder getOrder(int id) throws NoSuchIdException {
		return ArrayHandler.getElementById(id, orders);
	}

	public List<IOrder> getOrders() {
		return orders;
	}

	@SuppressWarnings("unchecked")
	public void readData() {
		Object fileData = FileWorker.read(FILE_PATH);
		if (fileData != null)
			orders = (List<IOrder>) fileData;
	}

	public void saveData() {
		FileWorker.save(orders, "data/order.ser");
	}

}
