package com.senla.library.dao.entityDAO;

import java.util.List;

import com.senla.library.api.annotation.di.Singleton;
import com.senla.library.api.bean.IBook;
import com.senla.library.api.dao.IBookDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.entity.Book;

@Singleton
public class BookDAO extends GenericDAO implements IBookDAO{
	private static IBookDAO instance;
	
	public static IBookDAO getInstance() {
		if (instance == null) {
			instance = new BookDAO();
		}
		return instance;
	} 
	
	public IBook get(int id) {
		return get(Book.class, id);
	}
	
	public List<Book> getAll(SortingCriteria sortingCriteria) {
		return getAll(Book.class, sortingCriteria);
	}
	
	public List<Book> getAll() {
		return getAll(Book.class);
	}
	
	
}
