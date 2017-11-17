package com.senla.library.manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.library.bean.IOrder;
import com.senla.library.bean.IOrderBookRelation;
import com.senla.library.enums.Status;
import com.senla.library.repository.OrderRepository;

public class OrderManager {

	private final OrderRepository orderRepository;

	public OrderManager(String filePath) {
		orderRepository = new OrderRepository(filePath);
	}

	public void addOrder(IOrder order) {
		orderRepository.addOrder(order);
	}

	public IOrder getOrder(int orderId) {
		return orderRepository.getOrder(orderId);
	}

	public List<IOrder> getOrders() {
		return orderRepository.getOrders();
	}

	public void addOrderBookRelation(IOrderBookRelation relation, double bookPrice) {
		IOrder order = getOrder(relation.getId());
		order.setTotalAmount(order.getTotalAmount() + bookPrice);
		order.getOrderBookList().add(relation);
	}

	public void completeOrder(int orderId) {
		IOrder order = orderRepository.getOrder(orderId);
		order.setStatus(Status.COMPLETED);
		order.setDate(new Date());
	}

	public void cancelOrder(IOrder order) {
		order.setStatus(Status.CANCELLED);
	}

	public List<IOrder> sortOrderList(Comparator<IOrder> comparator) {
		Collections.sort(getOrders(), comparator);
		return getOrders();
	}

	public double getTotalIncome(Date dateBefore, Date dateAfter) {
		double income = 0;
		for (IOrder order : orderRepository.getOrders())
			if (order != null)
				income += order.getTotalAmount();
		return income;
	}

	public int getCompletedOrderQuantity(Date dateBefore, Date dateAfter) {
		Integer count = 0;
		for (IOrder order : orderRepository.getOrders())
			if (order != null && order.getStatus() == Status.COMPLETED)
				++count;
		return count;
	}

	public void save() {
		orderRepository.saveData();
	}
}
