package com.senla.library.api.exception;

public class NoSuchIdException extends Exception{	
	private static final long serialVersionUID = -4491517548918913457L;	
	private Integer id;

	public NoSuchIdException(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "id=" + id + " doesn't exist!";
	}	
}
