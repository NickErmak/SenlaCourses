package com.senla.library.ui.action.order;

import java.util.HashMap;

import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.transmitter.response.ResponseOrderMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.ui.transmitter.Query;

public class OrderShowAllByPriceAction implements IAction{

	@Override
	public Query execute() {
		HashMap<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("type", MainMenuType.ORDER);
		actionInfo.put("orderType", OrderMenuType.SHOW_ALL);
		actionInfo.put("orderSortType", SortOrderType.BY_PRICE);
		actionInfo.put("message", ResponseOrderMessage.SHOW_ALL_BY_PRICE);
		return new Query(actionInfo);
	}
}
