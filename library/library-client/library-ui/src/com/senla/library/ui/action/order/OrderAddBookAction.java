package com.senla.library.ui.action.order;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseOrderMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.transmission.Query;
import com.senla.library.ui.action.book.BookShowAllAlphabeticallyAction;

public class OrderAddBookAction implements IAction {

	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("type", MainMenuType.ORDER);
		actionInfo.put("orderType", OrderMenuType.ADD_BOOK_TO_ORDER);
		actionInfo.put("message", ResponseOrderMessage.ADD_BOOK);
		actionInfo.put("inputMessage", QueryInputMessage.ADD_BOOK_TO_ORDER);
		actionInfo.put("inputAction", new BookShowAllAlphabeticallyAction());
		return new Query(actionInfo);
	}
}
