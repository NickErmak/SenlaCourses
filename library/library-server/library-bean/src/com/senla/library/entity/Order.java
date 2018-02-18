package com.senla.library.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.library.api.annotation.dao.BindingTable;
import com.senla.library.api.annotation.dao.ColumnDAO;
import com.senla.library.api.annotation.dao.EntityDAO;
import com.senla.library.api.annotation.dao.Id;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.OrderStatus;
import com.senla.library.util.DateConverter;

@EntityDAO(table = "orders", pk = "id")
public class Order extends Entity implements IOrder, Cloneable {	
	@Id
	private int id;		
	@ColumnDAO(name = "date")
	private Date date;	
	@ColumnDAO(name = "total_amount")
	private double totalAmount;
	@ColumnDAO(name = "status")
	private OrderStatus status;	
	@BindingTable(mappedBy = "order_id", oppositeMappedBy = "book_id")
	private List<Book> books;
	
	public Order() {
		books = new ArrayList<>();
	}
	
	public Order(int id, Date date, double totalAmount, OrderStatus status, List<Book> books) {
		this.id = id;
		this.date = date;
		this.totalAmount = totalAmount;
		this.status = status;
		this.books = books;
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public void setId(String id) {
		this.id = Integer.valueOf(id);
	}	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDate(String date) {
		this.date = DateConverter.stringToDate(date);
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = Double.valueOf(totalAmount);
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public void setStatus(String status) {
		this.status = OrderStatus.getStatus(status);
	}

	public List<Book> getBooks() {
		return books;
	}

	public void addBook(Book book) {
		books.add(book);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + DateConverter.dateToString(date) + ", totalAmount=" + totalAmount + ", status=" + status + ", books="
				+ books + "]";
	}	
	
	
}
