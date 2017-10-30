package com.senla.library.entity;

import java.util.Date;
import com.senla.library.repository.RequestRepository;
import com.senla.library.util.IdGenerator;

public class Request extends EntityId{
	
	private int id;
	private Date date;
	private int bookId;
	private Status status;
	
	public Request(int bookId) {
		id = IdGenerator.generateId() + RequestRepository.ID_LAST_DIGIT;		
		this.bookId = bookId;
		status = Status.PROCESSING;
		date = null;
	}
	
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

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	public Status getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}	
		
}
