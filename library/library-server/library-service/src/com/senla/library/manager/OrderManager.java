package com.senla.library.manager;

import static com.senla.library.csv.CSVHandler.CSVFileReader.read;
import static com.senla.library.csv.CSVHandler.CSVFileWriter.write;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.senla.library.api.bean.BookStatus;
import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.OrderStatus;
import com.senla.library.api.dao.IBookDAO;
import com.senla.library.api.dao.IOrderDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.dao.entityDAO.DaoShell;
import com.senla.library.entity.Order;

public class OrderManager {
	private final IOrderDAO orderDAO;
	private final IBookDAO bookDAO;

	public OrderManager(String filePath) {
		orderDAO = DaoShell.getOrderDAO();
		bookDAO = DaoShell.getBookDAO();
	}

	public void addOrder(IOrder order) throws Exception {
		orderDAO.add(order);
	}

	public IOrder getOrder(int id) throws Exception {
		return orderDAO.getOrder(id);
	}

	public void updateOrder(IOrder order) throws Exception {
		Connection connection = null;
		try {
			connection = orderDAO.getConnection();
			connection.setAutoCommit(false);
			updateOrder(order);
			Iterator<? extends IBook> books = order.getBooks().iterator();
			while (books.hasNext()) {
				bookDAO.update(books.next());
			}
			connection.commit();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	public List<IOrder> getOrders() throws Exception {
		return orderDAO.getAll(Order.class);
	}

	public List<IOrder> getOrders(SortingCriteria sortingCriteria) throws Exception {
		return orderDAO.getAll(Order.class, sortingCriteria);
	}

	public List<IOrder> getCompletedOrders(SortingCriteria sortingCriteria, Date dateFrom, Date dateTo)
			throws Exception {
		Field date = Order.class.getDeclaredField("date");
		return orderDAO.getAll(Order.class, sortingCriteria, date, dateFrom, dateTo);
	}

	public void completeOrder(IOrder order) throws Exception {
		order.setStatus(OrderStatus.COMPLETED);
		order.setDate(new Date());
		Iterator<? extends IBook> books = order.getBooks().iterator();
		Connection connection = null;
		try {
			connection = bookDAO.getConnection();
			while (books.hasNext()) {
				IBook book = books.next();
				book.setStatus(BookStatus.SOLD);
				bookDAO.update(book);
			}
			updateOrder(order);
			connection.commit();
			connection.setAutoCommit(false);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	public void cancelOrder(IOrder order) throws Exception {
		order.setStatus(OrderStatus.CANCELLED);
		updateOrder(order);
	}

	public int getCompletedOrderCount(Date dateFrom, Date dateTo) throws Exception {
		return getCompletedOrders(dateFrom, dateTo).size();
	}

	public double getTotalIncome(Date dateFrom, Date dateTo) throws Exception {
		double totalIncome = 0;
		Iterator<IOrder> orders = getCompletedOrders(dateFrom, dateTo).iterator();
		while (orders.hasNext()) {
			totalIncome += orders.next().getTotalAmount();
		}
		return totalIncome;
	}

	public List<IOrder> getCompletedOrders(Date dateFrom, Date dateTo) throws Exception {
		Field date = Order.class.getDeclaredField("date");
		return orderDAO.getOrders(null, date, dateFrom, dateTo);
	}

	public IOrder cloneOrder(int id) throws CloneNotSupportedException, Exception {
		return getOrder(id).clone();
	}

	public void exportCSV(String filePath) throws Exception {
		List<IOrder> orders = getOrders();
		if (!orders.isEmpty()) {
			write(getOrders(), filePath);
		}
	}

	public void importCSV(String filePath) throws Exception {
		Iterator<Order> iteratorCSV = read(Order.class, filePath).iterator();
		Map<Integer, IOrder> mappedOrders = getMappedOrders();
		while (iteratorCSV.hasNext()) {
			IOrder orderCSV = iteratorCSV.next();
			if (mappedOrders.containsKey(orderCSV.getId())) {
				updateOrder(orderCSV);
			} else {
				addOrder(orderCSV);
			} 
		}
	}

	private Map<Integer, IOrder> getMappedOrders() throws Exception {
		Map<Integer, IOrder> mappedOrders = new HashMap<>();
		Iterator<IOrder> orders = getOrders().iterator();
		while (orders.hasNext()) {
			IOrder order = orders.next();
			mappedOrders.put(order.getId(), order);
		}
		return mappedOrders;
	}

	public void exit() throws Exception {
		orderDAO.exit();
	}
}
