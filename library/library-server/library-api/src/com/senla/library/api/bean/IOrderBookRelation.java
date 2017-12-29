package com.senla.library.api.bean;

public interface IOrderBookRelation {
	
	public int getBookId();
	public int getOrderId();	
	public IOrderBookRelation clone() throws CloneNotSupportedException;
}
