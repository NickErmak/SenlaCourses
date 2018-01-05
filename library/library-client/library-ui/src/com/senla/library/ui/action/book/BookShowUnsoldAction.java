package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.response.ResponseBookMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.transmission.Query;

public class BookShowUnsoldAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("method", "showUnsoldBooks");	
		actionInfo.put("message", ResponseBookMessage.UNSOLD_BOOKS);
		return new Query(actionInfo);
	}
}
