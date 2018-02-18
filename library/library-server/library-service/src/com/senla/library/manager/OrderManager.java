package com.senla.library.manager;

import static com.senla.library.csv.CSVHandler.CSVFileReader.read;
import static com.senla.library.csv.CSVHandler.CSVFileWriter.write;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.OrderStatus;
import com.senla.library.api.dao.IOrderDAO;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.dao.entityDAO.DaoShell;
import com.senla.library.entity.Order;

public class OrderManager {
	private final IOrderDAO orderDAO;

	public OrderManager(String filePath) {
		orderDAO = DaoShell.getOrderDAO();
	}

	public void addOrder(IOrder order) {
		orderDAO.add(order);
	}

	public IOrder getOrder(int orderId) {
		return orderDAO.get(orderId);
	}

	public void updateOrder(IOrder order) {
		orderDAO.update(order);
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrders() {
		return (List<Order>) orderDAO.getAll();
	}

	public void completeOrder(IOrder order) throws NoSuchIdException {
		order.setStatus(OrderStatus.COMPLETED);
		order.setDate(new Date());
		updateOrder(order);
	}

	public void cancelOrder(IOrder order) throws NoSuchIdException {
		order.setStatus(OrderStatus.CANCELLED);
		updateOrder(order);
	}

	public void exportCSV(String filePath) throws NonParseableException {
		List<Order> orders = getOrders();
		if (!orders.isEmpty()) {
			write(getOrders(), filePath);
		}
	}

	public void importCSV(String filePath) throws NonParseableException {
		Iterator<Order> iteratorCSV = read(Order.class, filePath).iterator();
		while (iteratorCSV.hasNext()) {
			IOrder orderCSV = iteratorCSV.next();
			IOrder order = getOrder(orderCSV.getId());
			if (order != null) {
				updateOrder(order);
			}
		}
	}
}
