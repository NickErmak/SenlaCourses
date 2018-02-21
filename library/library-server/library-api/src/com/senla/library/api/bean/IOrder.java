package com.senla.library.api.bean;

import java.util.Date;
import java.util.List;

public interface IOrder extends IEntity {
	
	void setStatus(OrderStatus orderStatus);
	void setDate(Date date);
	List<IBook> getBooks();
}
