package com.senla.library.ui.action;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.response.ResponseMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.ui.transmitter.Query;

public class ExitAction implements IAction {

	@Override
	public Query execute() {
		Map<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("type", MainMenuType.EXIT);
		actionInfo.put("message", ResponseMessage.EXIT);
		return new Query(actionInfo);
	}
}
