package com.senla.library.entity;

import java.util.Date;

import com.senla.library.bean.IRequest;
import com.senla.library.enums.Status;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Request extends Entity implements IRequest{
	
	private int id;
	private Date date;
	private int bookId;
	private Status status;
	
	public Request() {}
	
	public Request(int bookId) {
		id = IdGenerator.generateId() + IdGenerator.REQUEST_ID_LAST_DIGIT;		
		this.bookId = bookId;
		status = Status.PROCESSING;
		date = null;
	}
	
	public Request(String[] data) {
		id = Integer.valueOf(data[0]);
		date = DateConverter.stringToDate(data[1]);
		bookId = Integer.valueOf(data[2]);
		status = Status.getStatus(data[3]);	
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public int getBookId() {
		return bookId;
	}
	
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}	

	public Status getStatus() {
		return status;
	}	
	
	@Override
	public String toString() {
		return id + "  " + DateConverter.dateToString(date) + "  " + bookId + "  " + status;
	}	
			
}
