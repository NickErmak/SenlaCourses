package com.senla.library.entity;

import java.util.Date;

import com.senla.library.repository.BookRepository;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Book extends EntityId {

	private int id;
	private String title;
	private Date publicationDate;
	private double price;
	private String description;
	private boolean onStock;
	private Request request;
	private String requestId;
	private OrderBookRelation[] orderBookList;
	private String[] OrderBookRelationIdList;

	public Book(String title, Date publicationDate, double price, String description) {
		id = IdGenerator.generateId() + BookRepository.ID_LAST_DIGIT;
		this.title = title;
		this.publicationDate = publicationDate;
		this.price = price;
		this.description = description;
		orderBookList = new OrderBookRelation[BookRepository.MAX_ORDER];
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setOnStock(boolean onStock) {
		this.onStock = onStock;
	}

	public boolean isOnStock() {
		return onStock;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void addOrderBookRelation(OrderBookRelation relation) {
		orderBookList[ArrayHandler.getFreeCellIndex(orderBookList)] = relation;
	}

	public int getOrderBookRelationQuantity() {
		return ArrayHandler.getElementQuantity(orderBookList);
	}

	public String getOrderBookRelationId() {
		StringBuilder allRelationId = new StringBuilder();
		for (OrderBookRelation relation : orderBookList)
			if (relation == null)
				return null;
			else
				allRelationId.append(relation.getId()).append("id");
		return allRelationId.toString();
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setOrderBookRelationIdList(String[] orderBookRelationIdList) {
		OrderBookRelationIdList = orderBookRelationIdList;
	}

	public String[] getOrderBookRelationIdList() {
		return OrderBookRelationIdList;
	}

	@Override
	public String toString() {
		return "Book [title = " + title + ", publication date = " + DateConverter.dateToString(publicationDate)
				+ ", price = " + price + ", onStock = " + onStock + ", id = " + id + "]";
	}
}
