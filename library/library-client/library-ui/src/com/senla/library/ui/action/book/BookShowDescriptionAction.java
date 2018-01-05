package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseBookMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.transmission.Query;

public class BookShowDescriptionAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("message", ResponseBookMessage.SHOW_DESCRIPTION);
		actionInfo.put("method", "showBookDescription");					
		actionInfo.put("inputMessage", QueryInputMessage.GET_BOOK_ID);
		actionInfo.put("inputAction", new BookShowAllAlphabeticallyAction());
		return new Query(actionInfo);
	}
}
