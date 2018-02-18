package com.senla.library.api.bean;

import java.util.Date;

public interface IOrder extends IEntity {
	
	void setStatus(OrderStatus orderStatus);
	void setDate(Date date);
}
