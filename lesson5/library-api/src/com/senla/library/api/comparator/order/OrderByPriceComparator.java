package com.senla.library.api.comparator.order;

import java.util.Comparator;

import com.senla.library.api.bean.IOrder;

public class OrderByPriceComparator implements Comparator<IOrder> {

	@Override
	public int compare(IOrder order1, IOrder order2) {
		if (order1 != null && order2 != null)
			return Double.compare(order1.getTotalAmount(), order2.getTotalAmount());
		else
			return 0;
	}

}
