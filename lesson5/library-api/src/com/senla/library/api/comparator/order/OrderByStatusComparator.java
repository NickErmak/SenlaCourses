package com.senla.library.api.comparator.order;

import java.util.Comparator;

import com.senla.library.api.bean.IOrder;

public class OrderByStatusComparator implements Comparator<IOrder> {

	@Override
	public int compare(IOrder order1, IOrder order2) {
		if (order1 != null && order2 != null)
			return order1.getStatus().compareTo(order2.getStatus());
		else
			return 0;
	}

}
