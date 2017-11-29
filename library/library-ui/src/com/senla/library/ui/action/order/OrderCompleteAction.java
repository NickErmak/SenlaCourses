package com.senla.library.ui.action.order;

import java.util.HashMap;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseOrderMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.ui.transmitter.Query;

public class OrderCompleteAction implements IAction {

	@Override
	public Query execute() {
		HashMap<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("type", MainMenuType.ORDER);
		actionInfo.put("orderType", OrderMenuType.COMPLETE);
		actionInfo.put("message", ResponseOrderMessage.COMPLETE);
		actionInfo.put("inputMessage", QueryInputMessage.GET_ORDER_ID);
		return new Query(actionInfo);
	}

}
