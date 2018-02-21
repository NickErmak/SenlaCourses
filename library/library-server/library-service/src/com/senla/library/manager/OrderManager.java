package com.senla.library.manager;

import static com.senla.library.csv.CSVHandler.CSVFileReader.read;
import static com.senla.library.csv.CSVHandler.CSVFileWriter.write;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.OrderStatus;
import com.senla.library.api.dao.IOrderDAO;
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

	public IOrder getOrder(int id) throws Exception {
		return orderDAO.getOrder(id);
	}

	public void updateOrder(IOrder order) {
		orderDAO.update(order);
	}

	public List<IOrder> getOrders() throws Exception {
		return orderDAO.getAll(Order.class);
	}

	public void completeOrder(IOrder order) {
		order.setStatus(OrderStatus.COMPLETED);
		order.setDate(new Date());
		updateOrder(order);
	}

	public void cancelOrder(IOrder order) {
		order.setStatus(OrderStatus.CANCELLED);
		updateOrder(order);
	}

	public int getCompletedOrderCount(Date dateFrom, Date dateTo) throws Exception {
		int count = 0;
		Field date = Order.class.getDeclaredField("date");
		count = orderDAO.getOrders(null, date, dateFrom, dateTo).size();
		return count;
	}

	public void exportCSV(String filePath) throws Exception {
		List<IOrder> orders = getOrders();
		if (!orders.isEmpty()) {
			write(getOrders(), filePath);
		}
	}

	public void importCSV(String filePath) throws Exception {
		Iterator<Order> iteratorCSV = read(Order.class, filePath).iterator();
		while (iteratorCSV.hasNext()) {
			IOrder orderCSV = iteratorCSV.next();
			IOrder order = getOrder(orderCSV.getId());
			if (order != null) {
				Connection connection = orderDAO.getConnection();
				connection.setAutoCommit(false);
				updateOrder(order);	
				Iterator<IBook> books = order.getBooks().iterator();
				while (books.hasNext()) {
					DaoShell.getBookDAO().update(books.next());
				}
				connection.commit();
				connection.setAutoCommit(true);
			}
		}
	}
}
