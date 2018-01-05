package com.senla.library.transmission;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.senla.library.api.transmitter.query.IQuery;

public class Query implements IQuery, Serializable{	
	private static final long serialVersionUID = -5469438917065058404L;
	private Map<String, Object> actionInfo = new HashMap<>();	
	
	public Query(Map<String, Object> actionInfo) {
		this.actionInfo = actionInfo;
	}
	
	@Override
	public void putInput(Object input) { 
		actionInfo.put("parameter", input);
	}
	
	@Override
	public Map<String, Object> getActionInfo() {
		return actionInfo;
	}	

	@Override
	public String toString() {
		return actionInfo.get("inputMessage").toString();
	}			
}
