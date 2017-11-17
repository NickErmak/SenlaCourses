package com.senla.library.bean;

import java.util.Date;

import com.senla.library.enums.Status;

public interface IRequest extends IEntity {

	public int getBookId();
	public Date getDate();
	public void setStatus(Status status);
	public void setDate(Date date);

}
