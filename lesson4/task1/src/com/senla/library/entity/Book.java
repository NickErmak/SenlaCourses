package com.senla.library.entity;

import java.util.Date;

import com.senla.library.repository.BookRepository;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Book extends Entity {

	private int id;
	private String title;
	private Date publicationDate;
	private double price;
	private String description;
	private boolean onStock;
	private int requestId;
	private OrderBookRelation[] orderBookList;

	public Book() {
		orderBookList = new OrderBookRelation[BookRepository.MAX_ORDER];
	}
	
	public Book(String title, Date publicationDate, double price, String description) {
		this();
		id = IdGenerator.generateId() + BookRepository.ID_LAST_DIGIT;
		this.title = title;
		this.publicationDate = publicationDate;
		this.price = price;
		this.description = description;		
	}

	public Book(String[] data) {
		this();
		id = Integer.valueOf(data[0]);
		title = data[1];
		publicationDate = DateConverter.stringToDate(data[2]);
		price = Double.valueOf(data[3]);
		description = data[4];
		onStock = Boolean.valueOf(data[5]);
		requestId = Integer.valueOf(data[6]);
	}

	@Override
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public void setOnStock(boolean onStock) {
		this.onStock = onStock;
	}

	public boolean isOnStock() {
		return onStock;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public OrderBookRelation[] getOrderBookList() {
		return orderBookList;
	}

	@Override
	public Entity convertEntity(String[] data) {
		return new Book(data);
	}

	@Override
	public String toString() {
		return id + "%%" + title + "%%" + DateConverter.dateToString(publicationDate) + "%%" + price + "%%"
				+ description + "%%" + onStock + "%%" + requestId;
	}
}
