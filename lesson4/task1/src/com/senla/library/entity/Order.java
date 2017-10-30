package com.senla.library.entity;

import java.util.Date;
import com.senla.library.repository.OrderRepository;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Order extends EntityId {

	private int id;
	private String name;
	private Date date;
	private double totalAmount;
	private Status status;
	private OrderBookRelation[] orderBookList;
	private String[] OrderBookRelationIdList;

	public Order(String name) {
		id = IdGenerator.generateId() + OrderRepository.ID_LAST_DIGIT;
		this.name = name;
		totalAmount = 0;
		status = Status.PROCESSING;
		orderBookList = new OrderBookRelation[OrderRepository.MAX_BOOK];
	}

	public int getId() {
		return id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount += totalAmount;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void addOrderBookRelation(OrderBookRelation relation) {
		orderBookList[ArrayHandler.getFreeCellIndex(orderBookList)] = relation;
	}

	public OrderBookRelation[] getOrderBookList() {
		return orderBookList;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public String getOrderBookRelationId() {
		StringBuilder allRelationId = new StringBuilder();
		for (OrderBookRelation relation : orderBookList)
			if (relation == null)
				return null;
			else
				allRelationId.append(relation.getBook().getId()).append("id");
		return allRelationId.toString();
	}

	@Override
	public String toString() {

		String dateToString = "null";
		if (date != null)
			dateToString = DateConverter.dateToString(date);
		return "Order [name = " + name + "date = " + dateToString + ", " + "totalAmount = " + totalAmount + ""
				+ ", status = " + status.statusType + ", id = " + id + "]";
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrderBookList(OrderBookRelation[] orderBookList) {
		this.orderBookList = orderBookList;
	}

	public void setOrderBookRelationIdList(String[] orderBookRelationIdList) {
		OrderBookRelationIdList = orderBookRelationIdList;
	}

	public String[] getOrderBookRelationIdList() {
		return OrderBookRelationIdList;
	}

}
