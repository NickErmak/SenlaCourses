package com.senla.library.api.bean;

public enum BookStatus {
	CHARTERED("chartered"), ON_STORAGE("on storage"), ON_ORDER("on order"), SOLD("sold");

	private String statusType;

	private BookStatus(String statusType) {
		this.statusType = statusType;
	}	

	public static BookStatus getStatus(String status) {
		if (status.equals(CHARTERED.statusType))
			return CHARTERED;
		else if (status.equals(ON_STORAGE.statusType))
			return ON_STORAGE;
		else if (status.equals(ON_ORDER.statusType))
			return ON_ORDER;
		else if (status.equals(SOLD.statusType))
			return SOLD;
		else
			return null;
	}

	public String toString() {
		return statusType;
	}
}
