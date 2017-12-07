package com.senla.library.api.transmitter.query;

import java.util.Map;

public interface IQuery {
	
	public Map<String, Object> getActionInfo();
	public void putInput(String input);
}
