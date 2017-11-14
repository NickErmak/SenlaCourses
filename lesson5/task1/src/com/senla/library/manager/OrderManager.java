package com.senla.library.manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.library.entity.Order;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.entity.Status;
import com.senla.library.repository.OrderRepository;

public class OrderManager {

	private final OrderRepository orderRep;

	public OrderManager(String filePath) {
		orderRep = new OrderRepository(filePath);
	}

	public void addOrder(Order order) {
		orderRep.addOrder(order);
	}

	public Order getOrder(int orderId) {
		return orderRep.getOrder(orderId);
	}

	public List<Order> getOrders() {
		return orderRep.getOrders();
	}

	public void addOrderBookRelation(OrderBookRelation relation, double bookPrice) {
		Order order = getOrder(relation.getId());
		order.setTotalAmount(order.getTotalAmount() + bookPrice);
		order.getOrderBookList().add(relation);
	}

	public void completeOrder(int orderId) {
		Order order = orderRep.getOrder(orderId);
		order.setStatus(Status.COMPLETED);
		order.setDate(new Date());
	}

	public void cancelOrder(Order order) {
		order.setStatus(Status.CANCELLED);
	}

	public List<Order> sortOrderList(Comparator<Order> comparator) {
		Collections.sort(getOrders(), comparator);
		return getOrders();
	}

	public double getTotalIncome(Date dateBefore, Date dateAfter) {
		double income = 0;
		for (Order order : orderRep.getOrders())
			if (order != null)
				income += order.getTotalAmount();
		return income;
	}

	public String getCompletedOrderQuantity(Date dateBefore, Date dateAfter) {
		Integer count = 0;
		for (Order order : orderRep.getOrders())
			if (order != null && order.getStatus() == Status.COMPLETED)
				++count;
		return count.toString();
	}

	public void save() {
		orderRep.saveData();
	}
}
