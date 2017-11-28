package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.BookMenuType;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.ui.transmitter.Query;

public class BookShowQueryAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("type", MainMenuType.BOOK);
		actionInfo.put("bookType", BookMenuType.SHOW_QUERY);		
		return new Query(actionInfo);
	}

}
