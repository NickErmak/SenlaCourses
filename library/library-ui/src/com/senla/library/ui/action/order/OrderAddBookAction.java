package com.senla.library.ui.action.order;

import java.util.HashMap;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseOrderMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.ui.transmitter.Query;

public class OrderAddBookAction implements IAction {

	@Override
	public Query execute() {
		HashMap<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("type", MainMenuType.ORDER);
		actionInfo.put("orderType", OrderMenuType.ADD_BOOK_TO_ORDER);
		actionInfo.put("message", ResponseOrderMessage.ADD_BOOK);
		actionInfo.put("inputMessage", QueryInputMessage.ADD_BOOK_TO_ORDER);
		return new Query(actionInfo);
	}
}
