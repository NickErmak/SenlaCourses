package com.senla.library.bean;

import java.util.Date;
import java.util.List;

import com.senla.library.enums.Status;

public interface IOrder extends IEntity {
	public double getTotalAmount();
	public List<IOrderBookRelation> getOrderBookList();
	public void setTotalAmount(double totalAmount);
	public void setDate(Date date);
	public Status getStatus();
	public void setStatus(Status status);
	public Date getDate();
}
