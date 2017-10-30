package com.senla.library.comparator.order;

import java.util.Comparator;
import com.senla.library.entity.Order;

public class OrderByStatusComparator implements Comparator<Order> {

	@Override
	public int compare(Order order1, Order order2) {
		if (order1 != null && order2 != null)
			return order1.getStatus().compareTo(order2.getStatus());
		else
			return 0;
	}

}
