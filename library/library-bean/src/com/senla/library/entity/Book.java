package com.senla.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.senla.library.util.DateConverter.dateToString;
import static com.senla.library.util.DateConverter.stringToDate;

import com.senla.library.annotation.csv.CsvConstructor;
import com.senla.library.annotation.csv.CsvEntity;
import com.senla.library.annotation.csv.CsvProperty;
import com.senla.library.annotation.csv.CsvProperty.PropertyType;
import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.util.IdGenerator;

@CsvEntity(fileName = "book.csv", valuesSeparator = ';', entityId = "id")
public class Book extends Entity implements IBook, Cloneable {
	private static final long serialVersionUID = 3277417789616455081L;	
	private int id;	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private String title;	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private Date publicationDate;	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private double price;	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	private String description;	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
	private boolean onStock;	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
	private Integer requestId;
	private List<IOrderBookRelation> orderBookList;

	public Book(String title, Date publicationDate, Double price, String description, boolean onStock) {
		this.title = title;
		this.publicationDate = publicationDate;
		this.price = price;
		this.description = description;
		this.onStock = onStock;
		id = IdGenerator.generateId() + IdGenerator.BOOK_ID_LAST_DIGIT;
		orderBookList = new ArrayList<>();
	}

	@CsvConstructor
	public Book(String[] bookCSVArray) {
		id = Integer.valueOf(bookCSVArray[0]);
		title = bookCSVArray[1];
		publicationDate = stringToDate(bookCSVArray[2]);
		price = Double.valueOf(bookCSVArray[3]);
		description = bookCSVArray[4];
		onStock = Boolean.valueOf(bookCSVArray[5]);
		if (!bookCSVArray[6].equals("null")) {
			requestId = Integer.valueOf(bookCSVArray[6]);
		}
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
	public String[] toStringCSV() {
		String[] arrayCSV = { String.valueOf(id), title, dateToString(publicationDate), String.valueOf(price),
				description, String.valueOf(requestId), String.valueOf(onStock) };
		return arrayCSV;
	}

	@Override
	public String toString() {
		return "id=" + id + ", title=" + title + ", date=" + dateToString(publicationDate) + ", price=" + price
				+ ", onStock=" + onStock;
	}
}
