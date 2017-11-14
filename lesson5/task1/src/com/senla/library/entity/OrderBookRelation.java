package com.senla.library.entity;

public class OrderBookRelation extends Entity{
	
	private int orderId;
	private int bookId;
	
	public OrderBookRelation() {}
	
	public OrderBookRelation(int orderId, int bookId) {
		this.orderId = orderId;
		this.bookId = bookId;
	}
	
	public OrderBookRelation(String[] data) {
		orderId = Integer.valueOf(data[0]);
		bookId = Integer.valueOf(data[1]);
	}
	
	public int getBookId() {
		return bookId;
	}
	
	@Override
	public int getId() {
		return orderId;
	}
	
	@Override
	public String toString() {
		return "" + orderId + "  " + bookId;
	}
		
}
