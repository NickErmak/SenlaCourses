package com.senla.library.api.config;

import java.util.Map;

public interface IPropertyReader {
	
	public Map<PropertyUnit, String> load();
}
