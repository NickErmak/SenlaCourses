package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.comparator.book.SortBookType;
import com.senla.library.api.transmitter.response.ResponseBookMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.transmission.Query;

public class BookShowAllByPriceAction implements IAction {

	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("message", ResponseBookMessage.SHOW_ALL_BY_PRICE);
		actionInfo.put("method", "showBooks");		
		actionInfo.put("parameter", SortBookType.BY_PRICE);
		return new Query(actionInfo);
	}
}
