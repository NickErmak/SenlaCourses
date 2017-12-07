package com.senla.library.ui.action.order;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.transmitter.response.ResponseOrderMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.ui.transmitter.Query;

public class OrderShowAllByDateAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("type", MainMenuType.ORDER);
		actionInfo.put("orderType", OrderMenuType.SHOW_ALL);
		actionInfo.put("orderSortType", SortOrderType.BY_EXECUTION_DATE);
		actionInfo.put("message", ResponseOrderMessage.SHOW_ALL_BY_DATE);
		return new Query(actionInfo);
	}
}
