package com.senla.library.comparator.order;

import java.util.Comparator;
import com.senla.library.entity.Order;

public class OrderByPriceComparator implements Comparator<Order> {

	@Override
	public int compare(Order order1, Order order2) {
		if (order1 != null && order2 != null)
			return Double.compare(order1.getTotalAmount(), order2.getTotalAmount());
		else
			return 0;
	}

}
