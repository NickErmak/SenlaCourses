package com.senla.library.ui.action;

import java.util.HashMap;

import com.senla.library.ui.IAction;
import com.senla.library.ui.menu.MainMenuType;
import com.senla.library.ui.transmission.Query;

public class ExitAction implements IAction {

	@Override
	public Query execute() {
		HashMap<String, Object> actionInfo = new HashMap<>();
		actionInfo.put("Type", MainMenuType.Exit);
		return new Query(actionInfo);
	}
}
