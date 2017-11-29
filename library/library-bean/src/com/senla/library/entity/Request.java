package com.senla.library.entity;

import java.util.Date;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.bean.Status;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Request extends Entity implements IRequest{
	
	private static final long serialVersionUID = 6561901578226972677L;
	private int id;
	private Date date;
	private int bookId;
	private Status status;
	
	public Request(int bookId) {
		id = IdGenerator.generateId() + IdGenerator.REQUEST_ID_LAST_DIGIT;		
		this.bookId = bookId;
		status = Status.PROCESSING;
		date = null;
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
		return "Request [id=" + id + ", date=" + DateConverter.dateToString(date) + ", bookId=" + bookId + ", status=" + status + "]";
	}	
	
	
			
}
