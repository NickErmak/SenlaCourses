package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IOrderRepository;
import com.senla.library.entity.Order;
import com.senla.library.util.ArrayHandler;

public class OrderRepository implements IOrderRepository{
	
	private static final String FILE_PATH = "data/order.txt";
	private static OrderRepository instance;
	private TextFileWorker textFileWorker;
	private List<IOrder> orderList;

	private OrderRepository() {
		textFileWorker = new TextFileWorker(FILE_PATH);
		orderList = new ArrayList<>();
		readData();
	}
	
	public static OrderRepository getInstance() throws NoSuchIdException {
		if (instance == null)
			instance = new OrderRepository();
		return instance;
	}

	public void addOrder(IOrder order) {
		orderList.add(order);
	}

	public IOrder getOrder(int id) throws NoSuchIdException {
		return ArrayHandler.getElementById(id, orderList);
	}

	public List<IOrder> getOrders() {
		return orderList;
	}

	public void readData() {
		for (String order : textFileWorker.readFromFile())
			addOrder(new Order(order.split("  ")));
	}

	public void saveData() {
		textFileWorker.writeToFile(ArrayHandler.getStringArray(orderList));
	}

}
