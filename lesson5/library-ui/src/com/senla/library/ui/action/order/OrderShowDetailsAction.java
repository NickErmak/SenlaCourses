package com.senla.library.ui.action.order;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseOrderMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.ui.transmitter.Query;

public class OrderShowDetailsAction implements IAction {

	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("type", MainMenuType.ORDER);
		actionInfo.put("orderType", OrderMenuType.SHOW_DETAILS);
		actionInfo.put("message", ResponseOrderMessage.SHOW_DETAILS);
		actionInfo.put("inputMessage", QueryInputMessage.GET_ORDER_ID);
		return new Query(actionInfo);
	}
}
