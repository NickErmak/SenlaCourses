package com.senla.library.dao.entityDAO;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import com.senla.library.api.annotation.di.Singleton;
import com.senla.library.api.bean.IBook;
import com.senla.library.api.dao.IBookDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.entity.Book;

@Singleton
public class BookDAO extends AbstractDAO<IBook> implements IBookDAO{
	
	public BookDAO() throws Exception {
		super();	
	}

	private static BookDAO instance;

	public static BookDAO getInstance() throws Exception {
		if (instance == null) {
			instance = new BookDAO();
		}
		return instance;
	}

	@Override
	public IBook getBook(int id) throws Exception {
		return  super.get(Book.class, id);
	}

	@Override
	public List<IBook> getBooks() throws Exception {		
		return super.getAll(Book.class);
	}

	@Override
	public List<IBook> getBooks(SortingCriteria sortingCriteria) throws Exception {		
		return super.getAll(Book.class, sortingCriteria);
	}

	@Override
	public List<IBook> getBooks(SortingCriteria sortingCriteria, Field date, Date dateFrom) throws Exception {		
		return super.getAll(Book.class, sortingCriteria, date, dateFrom);
	}

	@Override
	public List<IBook> getBooks(SortingCriteria sortingCriteria, Field date, Date dateFrom, Date dateTo) throws Exception {		
		return super.getAll(Book.class, sortingCriteria, date, dateFrom, dateTo);
	}
}
