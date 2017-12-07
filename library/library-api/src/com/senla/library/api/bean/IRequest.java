package com.senla.library.api.bean;

import java.util.Date;

public interface IRequest extends IEntity {
	
	public int getBookId();
	public Date getDate();
	public void setStatus(Status status);
	public void setDate(Date date);	
}
