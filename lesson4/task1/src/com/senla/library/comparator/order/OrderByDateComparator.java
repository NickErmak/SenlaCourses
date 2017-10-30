package com.senla.library.comparator.order;

import java.util.Comparator;
import com.senla.library.entity.Order;

public class OrderByDateComparator implements Comparator<Order> {

	@Override
	public int compare(Order order1, Order order2) {
		if (order1 != null && order2 != null)
			return order1.getDate().compareTo(order2.getDate());
		else
			return 0;
	}

}
