package com.senla.library.api.comparator.order;

import java.util.Comparator;
import java.util.Date;

import com.senla.library.api.bean.IOrder;

public class OrderByDateComparator implements Comparator<IOrder> {

	@Override
	public int compare(IOrder order1, IOrder order2) {
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
