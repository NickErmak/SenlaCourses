package com.senla.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.bean.Status;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Order extends Entity implements IOrder{

	private int id;
	private String name;
	private Date date;
	private double totalAmount;
	private Status status;
	private List<IOrderBookRelation> orderBookList;

	public Order() {
		orderBookList = new ArrayList<>();
	}
	
	public Order(String name) {
		this();
		id = IdGenerator.generateId() + IdGenerator.ORDER_ID_LAST_DIGIT;
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
	
	@Override
	public double getTotalAmount() {
		return totalAmount;
	}

	@Override
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public List<IOrderBookRelation> getOrderBookList() {
		return orderBookList;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}	

	@Override
	public String toString() {
		return id + "  " + name + "  " + DateConverter.dateToString(date) + "  " + totalAmount + "  " + status;
	}

}
