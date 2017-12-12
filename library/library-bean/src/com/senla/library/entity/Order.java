package com.senla.library.entity;

import static com.senla.library.util.DateConverter.stringToDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.senla.library.annotation.csv.CsvConstructor;
import com.senla.library.annotation.csv.CsvEntity;
import com.senla.library.annotation.csv.CsvProperty;
import com.senla.library.annotation.csv.CsvProperty.PropertyType;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.bean.Status;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

@CsvEntity(fileName = "order.csv", valuesSeparator = ';', entityId = "id")
public class Order extends Entity implements IOrder, Cloneable {
	private static final long serialVersionUID = 8796093798515742852L;
	private int id;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private String name;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private Date date;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private double totalAmount;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	private Status status;
	@CsvProperty(propertyType = PropertyType.CompositeProperty, columnNumber = 5, keyField = "bookId")
	private List<IOrderBookRelation> orderBookList;

	public Order(String name) {
		this.name = name;
		orderBookList = new ArrayList<>();
		id = IdGenerator.generateId() + IdGenerator.ORDER_ID_LAST_DIGIT;
		totalAmount = 0;
		status = Status.PROCESSING;
	}

	@CsvConstructor
	public Order(String[] orderCSVArray) {
		orderBookList = new ArrayList<>();
		id = Integer.valueOf(orderCSVArray[0]);
		name = orderCSVArray[1];
		date = stringToDate(orderCSVArray[2]);
		totalAmount = Double.valueOf(orderCSVArray[3]);
		status = Status.getStatus(orderCSVArray[4]);
		if (!orderCSVArray[5].equals("")) {
			String separator = String.valueOf(this.getClass().getAnnotation(CsvEntity.class).valuesSeparator());
			createRelations(orderCSVArray[5].split(separator));
		}
	}

	private void createRelations(String[] bookIdArray) {
		for (String bookId : bookIdArray) {
			int intBookId = Integer.valueOf(bookId.trim());
			orderBookList.add(new OrderBookRelation(id, intBookId));
		}
	}

	@Override
	public int getId() {
		return id;
	}

	private void nullId() {
		id = 0;
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IOrder clone() throws CloneNotSupportedException {
		Order cloneOrder = (Order) super.clone();
		cloneOrder.nullId();
		ListIterator<IOrderBookRelation> listIterator = orderBookList.listIterator();
		while (listIterator.hasNext()) {
			IOrderBookRelation relationClone = listIterator.next().clone();
			listIterator.set(relationClone);
		}
		return cloneOrder;
	}

	@Override
	public String[] toStringCSV() {
		String booksId = orderBookList.toString();
		String[] arrayCSV = { String.valueOf(id), DateConverter.dateToString(date), name,
				booksId.substring(1, booksId.length() - 1), String.valueOf(totalAmount), status.toString() };
		return arrayCSV;
	}

	@Override
	public String toString() {
		return "id=" + id + ", date=" + DateConverter.dateToString(date) + ", totalAmount=" + totalAmount + ", status="
				+ status + ", Books Id=" + orderBookList;
	}
}
