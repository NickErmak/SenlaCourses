package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.annotation.di.Singleton;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IOrderRepository;
import com.senla.library.util.CollectionHandler;
import com.senla.library.util.FileWorker;

@Singleton
public class OrderRepository implements IOrderRepository {
	private static IOrderRepository instance;
	private List<IOrder> orders;

	private OrderRepository() {
		orders = new ArrayList<>();
	}

	public static IOrderRepository getInstance() {
		if (instance == null) {
			instance = new OrderRepository();
		}
		return instance;
	}

	@Override
	public void addOrder(IOrder order) {
		orders.add(order);
	}

	@Override
	public IOrder getOrder(int id) throws NoSuchIdException {
		return CollectionHandler.getElementById(id, orders);
	}

	@Override
	public void refreshOrder(IOrder deprecatedorder, IOrder refreshedOrder) {
		orders.remove(deprecatedorder);
		orders.add(refreshedOrder);
	}

	@Override
	public List<IOrder> getOrders() {
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readData(String filePath) {
		Object fileData = FileWorker.read(filePath);
		if (fileData != null) {
			orders = (List<IOrder>) fileData;
		}
	}

	@Override
	public void saveData(String filePath) {
		FileWorker.save(orders, filePath);
	}
}
