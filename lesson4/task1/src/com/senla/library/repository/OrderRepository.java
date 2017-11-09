package com.senla.library.repository;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.Order;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.FileWorker;

public class OrderRepository {

	public static final int ID_LAST_DIGIT = 3;
	public static final int MAX_BOOK = 10; // max quantity for 1 order
	private TextFileWorker textFileWorker;
	private Order[] orderList;

	public OrderRepository(int orderMax, String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		orderList = new Order[orderMax];
		readData();
	}

	public void addOrder(Order order) {
		orderList[ArrayHandler.getFreeCellIndex(orderList)] = order;
	}

	public Order getOrder(int orderId) {
		return (Order) ArrayHandler.getElementById(orderId, orderList);
	}

	public Order[] getOrders() {
		return orderList;
	}

	public void readData() {
		FileWorker.readData(textFileWorker, orderList, new Order());
	}

	public void saveData() {
		FileWorker.saveData(textFileWorker, orderList);
	}

}
