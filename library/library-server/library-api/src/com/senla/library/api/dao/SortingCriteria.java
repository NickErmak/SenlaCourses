package com.senla.library.api.dao;

public enum SortingCriteria {
	ID("id"), ALPHABETICALLY("title"), BY_PUBLICATION_DATE("publication_date"), BY_PRICE("price"),
	BY_EXECUTION_DATE("date"), BY_TOTAL_AMOUNT("total_amount"), BY_STATUS ("status");

	private String message;

	private SortingCriteria(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}
