package com.senla.library.entity;

import java.util.Date;
import com.senla.library.repository.RequestRepository;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Request extends Entity{
	
	private int id;
	private Date date;
	private int bookId;
	private Status status;
	
	public Request() {}
	
	public Request(int bookId) {
		id = IdGenerator.generateId() + RequestRepository.ID_LAST_DIGIT;		
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

	public int getBookId() {
		return bookId;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	

	public Status getStatus() {
		return status;
	}	

	@Override
	public Entity convertEntity(String[] data) {
		return new Request(data);
	}

	@Override
	public String toString() {
		return id + "%%" + DateConverter.dateToString(date) + "%%" + bookId + "%%" + status;
	}	
			
}
