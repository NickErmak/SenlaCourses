package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IBookRepository;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.FileWorker;

public class BookRepository implements IBookRepository {

	private static BookRepository instance;
	private static final String FILE_PATH = "data/book.ser";
	private List<IBook> books;

	public static BookRepository getInstance() {
		if (instance == null)
			instance = new BookRepository();
		return instance;
	}

	private BookRepository() {
		books = new ArrayList<>();
		readData();
	}

	@Override
	public void addBook(IBook book) {
		books.add(book);
	}

	public IBook getBook(int id) throws NoSuchIdException {
		return ArrayHandler.getElementById(id, books);
	}

	public List<IBook> getBooks() {
		return books;
	}

	@SuppressWarnings("unchecked")
	public void readData() {
		Object fileData = FileWorker.read(FILE_PATH);
		if (fileData != null)
			books = (List<IBook>) fileData;
	}

	public void saveData() {
		FileWorker.save(books, FILE_PATH);
	}

}
