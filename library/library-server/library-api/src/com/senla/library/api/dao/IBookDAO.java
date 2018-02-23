package com.senla.library.api.dao;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IBook;

public interface IBookDAO extends GenericDAO<IBook> {
	
	IBook getBook(int id) throws Exception;
	List<IBook> getBooks() throws Exception;
	List<IBook> getBooks(SortingCriteria sortingCriteria) throws Exception;	
	List<IBook> getBooks(SortingCriteria sortingCriteria, Field date, Date dateFrom) throws Exception;	
	List<IBook> getBooks(SortingCriteria sortingCriteria, Field date, Date dateFrom, Date dateTo) throws Exception;		
}
