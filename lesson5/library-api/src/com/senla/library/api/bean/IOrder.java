package com.senla.library.api.bean;

import java.util.Date;
import java.util.List;

public interface IOrder extends IEntity {
	public double getTotalAmount();
	public List<IOrderBookRelation> getOrderBookList();
	public void setTotalAmount(double totalAmount);
	public void setDate(Date date);
	public Status getStatus();
	public void setStatus(Status status);
	public Date getDate();
	public Object clone() throws CloneNotSupportedException;
}
