package com.senla.library.entity;

import static com.senla.library.util.DateConverter.stringToDate;

import java.util.Date;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.bean.Status;
import com.senla.library.util.DateConverter;
import com.senla.library.util.IdGenerator;

public class Request extends Entity implements IRequest {
	private static final long serialVersionUID = 6561901578226972677L;
	private int id;
	private int bookId;
	private Date date;
	private Status status;

	public Request(int bookId) {
		id = IdGenerator.generateId() + IdGenerator.REQUEST_ID_LAST_DIGIT;
		this.bookId = bookId;
		status = Status.PROCESSING;
		date = null;
	}
	
	public Request(String[] requestCSVArray) {
		id = Integer.valueOf(requestCSVArray[0]);
		bookId = Integer.valueOf(requestCSVArray[1]);
		date = stringToDate(requestCSVArray[2]);
		status = Status.getStatus(requestCSVArray[3]);	
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

	public Status getStatus() {
		return status;
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
	public String[] toStringCSV() {
		String[] arrayCSV = { String.valueOf(id), String.valueOf(bookId), DateConverter.dateToString(date),
				status.toString() };
		return arrayCSV;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", date=" + DateConverter.dateToString(date) + ", bookId=" + bookId + ", status="
				+ status + "]";
	}
}
