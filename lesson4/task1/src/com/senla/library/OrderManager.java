package com.senla.library;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.senla.library.entity.*;
import com.senla.library.repository.OrderRepository;
import com.senla.library.util.Printer;

public class OrderManager {

	private static final String ORDER_DETAILS_MESSAGE = "Order details:";
	private static final String ORDER_COMPLETE_MESSAGE = "Order status: completed";
	

	private final OrderRepository orderRep;

	public OrderManager(int orderMax) {
		orderRep = new OrderRepository(orderMax);
	}

	public void addOrder(Order order) {
		orderRep.addOrder(order);
	}

	public Order getOrder(int orderId) {
		return orderRep.getOrder(orderId);
	}

	public Order[] getOrderList() {
		return orderRep.getOrderList();
	}

	public void calculateTotalAmount(OrderBookRelation relation) {
		relation.getOrder().setTotalAmount(relation.getBook().getPrice());
	}

	public void addOrderBookRelation(OrderBookRelation relation) {
		relation.getOrder().addOrderBookRelation(relation);
	}

	public void completeOrder(int orderId) {
		Order order = orderRep.getOrder(orderId);
		order.setStatus(Status.COMPLETED);
		order.setDate(new Date());
		Printer.print(ORDER_COMPLETE_MESSAGE);
	}

	public void cancelOrder(Order order) {
		order.setStatus(Status.CANCELLED);
	}

	public void showOrderDetails(int orderId) {
		Order order = getOrder(orderId);
		Printer.print(ORDER_DETAILS_MESSAGE);
		Printer.print(order);
		Printer.print(order.getOrderBookList());
	}

	public Order[] sortOrderList(Comparator<Order> comparator) {
		Arrays.sort(getOrderList(), comparator);
		return getOrderList();
	}

	public double getTotalIncome(Date dateBefore, Date dateAfter) {
		double income = 0;
		for (Order order : orderRep.getOrderList())
			income += order.getTotalAmount();
		return income;
	}
	
	public String getCompletedOrderQuantity(Date dateBefore, Date dateAfter) {
		Integer count = 0;
		for (Order order : orderRep.getOrderList())
			if (order.getStatus() == Status.COMPLETED) ++count;
		return count.toString();
	}
	
	public void save() {
		orderRep.save();
	}
}
