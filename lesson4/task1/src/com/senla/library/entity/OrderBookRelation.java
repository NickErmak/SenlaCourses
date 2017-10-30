package com.senla.library.entity;

public class OrderBookRelation extends EntityId{
	
	private Order order;
	private Book book;
	
	public OrderBookRelation (Order order, Book book) {
		this.order = order;
		this.book = book;
	}	
	
	@Override
	public int getId() {
		return order.getId();
	}
	
	public Order getOrder() {
		return order;
	}

	public Book getBook() {
		return book;
	}

	@Override
	public String toString() {
		return book.toString();
	}
	
}
