package com.senla.library.api.repository;

import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.exception.NoSuchIdException;

public interface IBookRepository {

	void addBook(IBook book);
	IBook getBook(int id) throws NoSuchIdException;
	void refreshBook(IBook deprecatedBook, IBook refreshedBook);
	List<IBook> getBooks();
	void readData(String filePath);
	void saveData(String filePath);
}
