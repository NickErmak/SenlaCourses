package com.senla.library.ui.transmitter;

import java.util.HashMap;

import com.senla.library.api.transmitter.query.IQuery;

public class Query implements IQuery{

	private HashMap<String, Object> actionInfo = new HashMap<>();	
	
	public Query(HashMap<String, Object> actionInfo) {
		this.actionInfo = actionInfo;
	}

	public HashMap<String, Object> getActionInfo() {
		return actionInfo;
	}
	
	@Override
	public void putInput(String input) {
		actionInfo.put("input", input);
	}

	@Override
	public String toString() {
		return actionInfo.get("inputMessage").toString();
	}	
		
}
