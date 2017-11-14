package com.senla.library.comparator.order;

import java.util.Comparator;
import java.util.Date;

import com.senla.library.entity.Order;

public class OrderByDateComparator implements Comparator<Order> {

	@Override
	public int compare(Order order1, Order order2) {
		if (order1 != null && order2 != null) {
			Date date1 = order1.getDate();
			Date date2 = order2.getDate();
			if (date1 != null && date2 != null)
				return date1.compareTo(date2);
			else if (date1 != null && date2 == null)
				return -1;
			else
				return 1;
		}
		return 0;
	}

}
