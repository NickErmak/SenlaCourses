package com.senla.library.manager;

import static com.senla.library.csv.CSVHandler.CSVFileReader.read;
import static com.senla.library.csv.CSVHandler.CSVFileWriter.write;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.bean.Status;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.api.ui.ConsoleMessage;
import com.senla.library.entity.Order;
import com.senla.library.repository.OrderRepository;

public class OrderManager {
	private static Logger logger = Logger.getLogger(OrderManager.class);
	private final OrderRepository orderRepository;

	public OrderManager(String filePath) {
		orderRepository = OrderRepository.getInstance();
		orderRepository.readData(filePath);
	}

	public void addOrder(IOrder order) {
		orderRepository.addOrder(order);
	}

	public IOrder getOrder(int orderId) throws NoSuchIdException {
		return orderRepository.getOrder(orderId);
	}
	
	public void refreshOrder(IOrder deprecatedOrder, IOrder refreshedOrder) {
		orderRepository.refreshOrder(deprecatedOrder, refreshedOrder);
	}

	public List<IOrder> getOrders() {
		return orderRepository.getOrders();
	}

	public void addOrderBookRelation(IOrderBookRelation relation, double bookPrice) throws NoSuchIdException {
		IOrder order = getOrder(relation.getOrderId());
		order.setTotalAmount(order.getTotalAmount() + bookPrice);
		order.getOrderBookList().add(relation);
	}

	public void completeOrder(int orderId) throws NoSuchIdException {
		IOrder order = orderRepository.getOrder(orderId);
		order.setStatus(Status.COMPLETED);
		order.setDate(new Date());
	}

	public void cancelOrder(int id) throws NoSuchIdException {
		getOrder(id).setStatus(Status.CANCELLED);
	}

	public List<IOrder> sortOrderList(Comparator<IOrder> comparator) {
		Collections.sort(getOrders(), comparator);
		return getOrders();
	}

	public double getTotalIncome(Date dateBefore, Date dateAfter) {
		double income = 0;
		for (IOrder order : orderRepository.getOrders()) {
			if (order != null) {
				income += order.getTotalAmount();
			}
		}
		return income;
	}

	public int getCompletedOrderQuantity(Date dateBefore, Date dateAfter) {
		Integer count = 0;
		for (IOrder order : orderRepository.getOrders()) {
			if (order != null && order.getStatus() == Status.COMPLETED) {
				++count;
			}
		}
		return count;
	}

	public ConsoleMessage cloneOrder(int id) throws NoSuchIdException {
		try {
			addOrder((IOrder) getOrder(id).clone());
			return ConsoleMessage.SUCCESS;
		} catch (CloneNotSupportedException e) {
			logger.error(e);
			return ConsoleMessage.ERROR_NO_CLONEABLE;
		}
	}

	public void exportCSV(String filePath) throws NonParseableException {
		List<IOrder> orders = getOrders();
		if (!orders.isEmpty()) {
			write(getOrders(), filePath);
		}
	}

	public void importCSV(String filePath) throws NonParseableException {
		Iterator<Order> iteratorCSV = read(Order.class, filePath).iterator();
		while (iteratorCSV.hasNext()) {
			IOrder orderCSV = iteratorCSV.next();
			try {
				IOrder order = getOrder(orderCSV.getId());
				if (order != null) {
					refreshOrder(order, orderCSV);
				}
			} catch (NoSuchIdException e) {
				addOrder(orderCSV);
			}
		}
	}

	public void saveData(String filePath) {
		orderRepository.saveData(filePath);
	}
}
