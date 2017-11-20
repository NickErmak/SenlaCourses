package com.senla.library.ui.action.order;

import java.util.HashMap;

import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.ui.transmitter.Query;

public class OrderShowDataPeriodAction implements IAction{
	
	@Override
	public Query execute() {
		HashMap<String, Object> actionInfo = new HashMap<>();		
		actionInfo.put("type", MainMenuType.ORDER);
		actionInfo.put("orderType", OrderMenuType.SHOW_FOR_DATE_PERIOD);		
		return new Query(actionInfo);
	}
}
