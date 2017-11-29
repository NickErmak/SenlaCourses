package com.senla.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Book extends Entity implements IBook {

	private int id;
	private String title;
	private Date publicationDate;
	private double price;
	private String description;
	private boolean onStock;
	private int requestId;
	private List<IOrderBookRelation> orderBookList;

	public Book() {
		orderBookList = new ArrayList<>();
	}

	public Book(String title, Date publicationDate, double price, String description) {
		this();
		id = IdGenerator.generateId() + IdGenerator.BOOK_ID_LAST_DIGIT;
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

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Date getPublicationDate() {
		return publicationDate;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setOnStock(boolean onStock) {
		this.onStock = onStock;
	}

	@Override
	public boolean isOnStock() {
		return onStock;
	}

	@Override
	public int getRequestId() {
		return requestId;
	}

	@Override
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	@Override
	public List<IOrderBookRelation> getOrderBookList() {
		return orderBookList;
	}

	@Override
	public String toString() {
		return id + "  " + title + "  " + DateConverter.dateToString(publicationDate) + "  " + price + "  "
				+ description + "  " + onStock + "  " + requestId;
	}
}
