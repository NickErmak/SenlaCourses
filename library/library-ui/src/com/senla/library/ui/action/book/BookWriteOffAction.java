package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseBookMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.BookMenuType;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.ui.transmitter.Query;

public class BookWriteOffAction implements IAction {
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("type", MainMenuType.BOOK);
		actionInfo.put("bookType", BookMenuType.WRITE_OFF);		
		actionInfo.put("message", ResponseBookMessage.WRITE_OFF);
		actionInfo.put("inputMessage", QueryInputMessage.GET_BOOK_ID);
		actionInfo.put("inputAction", new BookShowAllAlphabeticallyAction());
		return new Query(actionInfo);
	}
}
