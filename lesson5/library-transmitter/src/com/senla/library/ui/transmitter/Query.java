package com.senla.library.ui.transmitter;

import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.IQuery;

public class Query implements IQuery{

	private Map<String, Object> actionInfo = new HashMap<>();	
	
	public Query(Map<String, Object> actionInfo) {
		this.actionInfo = actionInfo;
	}

	public Map<String, Object> getActionInfo() {
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
