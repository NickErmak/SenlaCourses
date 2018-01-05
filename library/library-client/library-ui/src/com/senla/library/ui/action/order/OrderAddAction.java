package com.senla.library.ui.action.order;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseOrderMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.transmission.Query;

public class OrderAddAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("message", ResponseOrderMessage.ADD);
		actionInfo.put("method", "addOrder");		
		actionInfo.put("inputMessage", QueryInputMessage.ADD_ORDER);
		return new Query(actionInfo);
	}
}
