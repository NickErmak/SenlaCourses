package com.senla.library.api.comparator.book;

public enum SortBookType {
	ALPHABETICALLY("alphbetically"), BY_PUBLICATION_DATE("by publication date"), BY_PRICE("by price"), BY_STOCK(
			"by stock availability");

	private String message;

	private SortBookType(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}
