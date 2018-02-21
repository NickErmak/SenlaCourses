package com.senla.library.dao.entityDAO;

import com.senla.library.api.annotation.di.Inject;
import com.senla.library.api.dao.IBookDAO;
import com.senla.library.api.dao.IOrderDAO;

public class DaoShell {		
	@Inject("com.senla.library.dao.entityDAO")
	private static IBookDAO bookDAO;	
	@Inject("com.senla.library.dao.entityDAO")
	private static IOrderDAO orderDAO;	
	
	public static IBookDAO getBookDAO() {
		return bookDAO;
	}
	
	public static IOrderDAO getOrderDAO() {
		return orderDAO;
	}	
}
