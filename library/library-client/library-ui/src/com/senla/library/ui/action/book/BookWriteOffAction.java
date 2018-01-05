package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseBookMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.transmission.Query;

public class BookWriteOffAction implements IAction {
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("message", ResponseBookMessage.WRITE_OFF);
		actionInfo.put("inputMessage", QueryInputMessage.GET_BOOK_ID);
		actionInfo.put("inputAction", new BookShowAllAlphabeticallyAction());
		actionInfo.put("method", "writeOffBook");	
		return new Query(actionInfo);
	}
}
