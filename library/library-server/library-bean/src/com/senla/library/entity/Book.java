package com.senla.library.entity;

import java.util.Date;

import com.senla.library.api.annotation.dao.ColumnDAO;
import com.senla.library.api.annotation.dao.EntityDAO;
import com.senla.library.api.annotation.dao.Id;
import com.senla.library.api.annotation.dao.ManyToOne;
import com.senla.library.api.bean.BookStatus;
import com.senla.library.api.bean.IBook;
import com.senla.library.util.DateConverter;

@EntityDAO(table = "books", pk = "id")
public class Book extends Entity implements IBook, Cloneable {	
	@Id	
	@ColumnDAO(name = "id")
	private int id;		
	@ManyToOne(mappedBy = "book_id")
	private BookInfo bookInfo;
	@ColumnDAO(name = "arrival_date")
	private Date arrivalDate;
	@ColumnDAO(name = "status")
	private BookStatus status;	
	
	public Book() {}
	
	public Book(int id, BookInfo bookInfo, Date arrivalDate, BookStatus status) {
		this.id = id;
		this.bookInfo = bookInfo;
		this.arrivalDate = arrivalDate;
		this.status = status;		
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

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = DateConverter.stringToDate(arrivalDate);
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}
	
	public void setStatus(String status) {
		this.status = BookStatus.getStatus(status);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookInfo=" + bookInfo + ", arrivalDate=" + DateConverter.dateToString(arrivalDate) + ", status=" + status
				+ "]";
	}	
	
	
}
