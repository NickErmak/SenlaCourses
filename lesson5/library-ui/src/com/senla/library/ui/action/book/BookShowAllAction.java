package com.senla.library.ui.action.book;

import java.util.HashMap;

import com.senla.library.ui.IAction;
import com.senla.library.ui.menu.BookMenuType;
import com.senla.library.ui.menu.MainMenuType;
import com.senla.library.ui.transmission.Query;

public class BookShowAllAction implements IAction {

	@Override
	public Query execute() {
		HashMap<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("Type", MainMenuType.Book);
		actionInfo.put("BookType", BookMenuType.ShowAll);
		return new Query(actionInfo);
	}

}
