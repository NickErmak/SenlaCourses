package com.senla.library.ui.action.book;

import java.util.HashMap;

import com.senla.library.api.comparator.book.SortBookType;
import com.senla.library.api.transmitter.response.ResponseBookMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.BookMenuType;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.ui.transmitter.Query;

public class BookShowAllAlphabeticallyAction implements IAction {

	@Override
	public Query execute() {
		HashMap<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("type", MainMenuType.BOOK);
		actionInfo.put("bookType", BookMenuType.SHOW_ALL);
		actionInfo.put("bookSortType", SortBookType.ALPHABETICALLY);
		actionInfo.put("message", ResponseBookMessage.SHOW_ALL_APHABETICALLY);
		return new Query(actionInfo);
	}

}
