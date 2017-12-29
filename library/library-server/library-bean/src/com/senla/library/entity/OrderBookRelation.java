package com.senla.library.entity;

import java.io.Serializable;

import com.senla.library.api.bean.IOrderBookRelation;

public class OrderBookRelation implements IOrderBookRelation, Serializable, Cloneable {
	private static final long serialVersionUID = -8295995103629543259L;
	private int orderId;
	private int bookId;

	public OrderBookRelation(int orderId, int bookId) {
		this.orderId = orderId;
		this.bookId = bookId;
	}

	@Override
	public int getBookId() {
		return bookId;
	}

	@Override
	public int getOrderId() {
		return orderId;
	}	
	
	@Override
	public IOrderBookRelation clone() throws CloneNotSupportedException {
		return (IOrderBookRelation) super.clone();
	}
	
	@Override
	public String toString() {
		return String.valueOf(bookId);
	}
}
