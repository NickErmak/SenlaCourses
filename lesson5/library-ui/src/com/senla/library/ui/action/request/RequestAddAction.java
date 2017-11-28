package com.senla.library.ui.action.request;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseRequestMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.RequestMenuType;
import com.senla.library.ui.transmitter.Query;

public class RequestAddAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("type", MainMenuType.REQUEST);
		actionInfo.put("requestType", RequestMenuType.ADD);	
		actionInfo.put("message", ResponseRequestMessage.ADD);
		actionInfo.put("inputMessage", QueryInputMessage.GET_BOOK_ID);
		return new Query(actionInfo);
	}

}
