package com.senla.library.ui.action.order;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseOrderMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.ui.transmitter.Query;

public class OrderShowCompletedQuantityAction implements IAction {

	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("type", MainMenuType.ORDER);
		actionInfo.put("orderType", OrderMenuType.SHOW_COMPLETED_QUANTITY);
		actionInfo.put("message", ResponseOrderMessage.ORDER_COMPLETED_QUANTITY);
		actionInfo.put("inputMessage", QueryInputMessage.INPUT_DATES);
		return new Query(actionInfo);
	}
}
