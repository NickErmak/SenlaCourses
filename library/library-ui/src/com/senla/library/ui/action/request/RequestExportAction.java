package com.senla.library.ui.action.request;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.response.ResponseRequestMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.RequestMenuType;
import com.senla.library.ui.transmitter.Query;

public class RequestExportAction implements IAction{
	
	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("type", MainMenuType.REQUEST);
		actionInfo.put("requestType", RequestMenuType.EXPORT);	
		actionInfo.put("message", ResponseRequestMessage.EXPORT);
		return new Query(actionInfo);
	}
}
