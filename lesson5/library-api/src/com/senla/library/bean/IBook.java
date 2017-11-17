package com.senla.library.bean;

import java.util.Date;
import java.util.List;

public interface IBook extends IEntity{
	
	public void setOnStock(boolean onStock);
	public boolean isOnStock();
	public void setRequestId(int requestId);
	public List<IOrderBookRelation> getOrderBookList();
	public Date getPublicationDate();
	public double getPrice();
	public String getTitle();
	public int getRequestId();	
}
