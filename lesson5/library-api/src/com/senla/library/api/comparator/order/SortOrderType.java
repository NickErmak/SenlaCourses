package com.senla.library.api.comparator.order;

public enum SortOrderType {
	
	BY_EXECUTION_DATE("by execution date"), BY_PRICE("by price"), BY_STATUS("by status");
	
	String message;

	private SortOrderType(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}
