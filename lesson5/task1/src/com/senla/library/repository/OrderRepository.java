package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.Order;
import com.senla.library.util.ArrayHandler;

public class OrderRepository {

	public static final int ID_LAST_DIGIT = 3;
	private TextFileWorker textFileWorker;
	private List<Order> orderList;

	public OrderRepository(String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		orderList = new ArrayList<>();
		readData();
	}

	public void addOrder(Order order) {
		orderList.add(order);
	}

	public Order getOrder(int id) {
		return ArrayHandler.getElementById(id, orderList);
	}

	public List<Order> getOrders() {
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
