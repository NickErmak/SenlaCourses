package com.senla.library.api.transmitter.query;

import java.util.HashMap;

public interface IQuery {
	public HashMap<String, Object> getActionInfo();
	public void putInput(String input);
}
