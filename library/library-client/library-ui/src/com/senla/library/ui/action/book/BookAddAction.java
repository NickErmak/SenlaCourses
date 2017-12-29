package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseBookMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.BookMenuType;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.transmission.Query;

public class BookAddAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("type", MainMenuType.BOOK);
		actionInfo.put("bookType", BookMenuType.ADD);	
		actionInfo.put("message", ResponseBookMessage.ADD);
		actionInfo.put("inputMessage", QueryInputMessage.ADD_BOOK);
		return new Query(actionInfo);
	}
}