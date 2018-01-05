package com.senla.library.ui.action.book;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.response.ResponseBookMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.transmission.Query;

public class BookImportAction implements IAction{

	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("method", "importCSVBook");		
		actionInfo.put("message", ResponseBookMessage.IMPORT);
		return new Query(actionInfo);
	}
}
