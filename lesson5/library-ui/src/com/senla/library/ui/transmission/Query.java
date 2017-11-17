package com.senla.library.ui.transmission;

import java.util.HashMap;

import com.senla.library.ui.IQuery;

public class Query implements IQuery{

	private HashMap<String, Object> actionInfo = new HashMap<>();	
	
	public Query(HashMap<String, Object> actionInfo) {
		this.actionInfo = actionInfo;
	}

	public HashMap<String, Object> getActionInfo() {
		return actionInfo;
	}

	@Override
	public String toString() {
		return "Query [actionInfo=" + actionInfo + "]";
	}	
		
}
