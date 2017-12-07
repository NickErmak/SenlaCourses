package com.senla.library.entity;

import static com.senla.library.util.DateConverter.stringToDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.bean.Status;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Order extends Entity implements IOrder, Cloneable {
	private static final long serialVersionUID = 8796093798515742852L;
	private int id;
	private String name;
	private Date date;
	private double totalAmount;
	private Status status;
	private List<IOrderBookRelation> orderBookList;

	public Order(String name) {
		this.name = name;
		orderBookList = new ArrayList<>();
		id = IdGenerator.generateId() + IdGenerator.ORDER_ID_LAST_DIGIT;
		totalAmount = 0;
		status = Status.PROCESSING;
	}

	public Order(String[] orderCSVArray) {
		id = Integer.valueOf(orderCSVArray[0]);
		date = stringToDate(orderCSVArray[1]);
		name = orderCSVArray[2];
		orderBookList = new ArrayList<>();
		totalAmount = Double.valueOf(orderCSVArray[4]);
		status = Status.getStatus(orderCSVArray[5]);
		if (!orderCSVArray[3].equals("")) {
			createRelations(orderCSVArray[3].split(","));
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
		return "id=" + id + "date=" + DateConverter.dateToString(date) + ", totalAmount=" + totalAmount + ", status="
				+ status + ", Books Id=" + orderBookList;
	}
}
