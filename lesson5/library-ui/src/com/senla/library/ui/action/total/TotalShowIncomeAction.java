package com.senla.library.ui.action.total;

import java.util.HashMap;

import com.senla.library.api.transmitter.query.QueryInputMessage;
import com.senla.library.api.transmitter.response.ResponseMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.TotalMenuType;
import com.senla.library.ui.transmitter.Query;

public class TotalShowIncomeAction implements IAction{

	@Override
	public Query execute() {
		HashMap<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("type", MainMenuType.TOTAL);
		actionInfo.put("totalType", TotalMenuType.SHOW_TOTAL_INCOME);	
		actionInfo.put("message", ResponseMessage.TOTAL_SHOW_INCOME);
		actionInfo.put("inputMessage", QueryInputMessage.INPUT_DATES);
		return new Query(actionInfo);
	}
}
