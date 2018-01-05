package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.ui.IAction;
import com.senla.library.transmission.Query;

public class BookShowQueryAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("method", "showBookQuery");				
		return new Query(actionInfo);
	}
}
