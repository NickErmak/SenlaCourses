package com.senla.library.entity;

import java.util.Date;

import com.senla.library.repository.OrderRepository;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Order extends Entity {

	private int id;
	private String name;
	private Date date;
	private double totalAmount;
	private Status status;
	private OrderBookRelation[] orderBookList;

	public Order() {
		orderBookList = new OrderBookRelation[OrderRepository.MAX_BOOK];
	}
	
	public Order(String name) {
		this();
		id = IdGenerator.generateId() + OrderRepository.ID_LAST_DIGIT;
		this.name = name;
		totalAmount = 0;
		status = Status.PROCESSING;		
	}

	public Order(String[] data) {
		this();
		id = Integer.valueOf(data[0]);
		name = data[1];
		date = DateConverter.stringToDate(data[2]);
		totalAmount = Double.valueOf(data[3]);
		status = Status.getStatus(data[4]);		
	}

	@Override
	public int getId() {
		return id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getName() {
		return name;
	}

	public void setOrderBookList(OrderBookRelation[] orderBookList) {
		this.orderBookList = orderBookList;
	}

	@Override
	public Entity convertEntity(String[] data) {
		return new Order(data);
	}

	@Override
	public String toString() {
		return id + "%%" + name + "%%" + DateConverter.dateToString(date) + "%%" + totalAmount + "%%" + status;
	}

}
