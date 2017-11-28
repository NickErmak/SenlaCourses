package com.senla.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Book extends Entity implements IBook {

	private static final long serialVersionUID = 3277417789616455081L;
	private int id;
	private String title;
	private Date publicationDate;
	private double price;
	private String description;
	private boolean onStock;
	private Integer requestId;
	private List<IOrderBookRelation> orderBookList;

	public Book(String title, Date publicationDate, Double price, String description) {
		orderBookList = new ArrayList<>();
		id = IdGenerator.generateId() + IdGenerator.BOOK_ID_LAST_DIGIT;
		this.title = title;
		this.publicationDate = publicationDate;
		this.price = price;
		this.description = description;
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
	public Integer getRequestId() {
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
		return "Book [id=" + id + ", title=" + title + ", publicationDate="
				+ DateConverter.dateToString(publicationDate) + ", price=" + price + ", description=" + description
				+ ", onStock=" + onStock + "]";
	}

}
