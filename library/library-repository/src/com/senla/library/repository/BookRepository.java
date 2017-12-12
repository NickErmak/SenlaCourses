package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.util.CollectionHandler;
import com.senla.library.util.FileWorker;

public class BookRepository {
	private static BookRepository instance;
	private List<IBook> books;

	private BookRepository() {
		books = new ArrayList<>();
	}

	public static BookRepository getInstance() {
		if (instance == null) {
			instance = new BookRepository();
		}
		return instance;
	}

	public void addBook(IBook book) {
		books.add(book);
	}

	public IBook getBook(int id) throws NoSuchIdException {
		return CollectionHandler.getElementById(id, books);
	}
	
	public void refreshBook(IBook deprecatedBook, IBook refreshedBook) {
		books.remove(deprecatedBook);
		books.add(refreshedBook);
	}

	public List<IBook> getBooks() {
		return books;
	}

	@SuppressWarnings("unchecked")
	public void readData(String filePath) {
		Object fileData = FileWorker.read(filePath);
		if (fileData != null) {
			books = (List<IBook>) fileData;
		}
	}

	public void saveData(String filePath) {
		FileWorker.save(books, filePath);
	}
}
