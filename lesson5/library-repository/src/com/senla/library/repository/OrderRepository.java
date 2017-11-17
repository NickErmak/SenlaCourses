package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.bean.IOrder;
import com.senla.library.entity.Order;
import com.senla.library.util.ArrayHandler;

public class OrderRepository implements IOrderRepository{
	
	private TextFileWorker textFileWorker;
	private List<IOrder> orderList;

	public OrderRepository(String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		orderList = new ArrayList<>();
		readData();
	}

	public void addOrder(IOrder order) {
		orderList.add(order);
	}

	public IOrder getOrder(int id) {
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
