package com.senla.library.manager;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.senla.library.entity.Order;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.entity.Status;
import com.senla.library.repository.OrderRepository;
import com.senla.library.util.ArrayHandler;

public class OrderManager {

	private final OrderRepository orderRep;

	public OrderManager(int orderMax, String filePath) {
		orderRep = new OrderRepository(orderMax, filePath);
	}

	public void addOrder(Order order) {
		orderRep.addOrder(order);
	}

	public Order getOrder(int orderId) {
		return orderRep.getOrder(orderId);
	}

	public Order[] getOrders() {
		return orderRep.getOrders();
	}

	public void addOrderBookRelation(OrderBookRelation relation, double bookPrice) {
		Order order = getOrder(relation.getId());
		order.setTotalAmount(order.getTotalAmount() + bookPrice);
		OrderBookRelation[] relationList = order.getOrderBookList();
		relationList[ArrayHandler.getFreeCellIndex(relationList)] = relation;
	}

	public void completeOrder(int orderId) {
		Order order = orderRep.getOrder(orderId);
		order.setStatus(Status.COMPLETED);
		order.setDate(new Date());
	}

	public void cancelOrder(Order order) {
		order.setStatus(Status.CANCELLED);
	}

	public Order[] sortOrderList(Comparator<Order> comparator) {
		Arrays.sort(getOrders(), comparator);
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
