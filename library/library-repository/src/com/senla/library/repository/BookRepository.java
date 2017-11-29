package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.api.bean.IBook;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IBookRepository;
import com.senla.library.entity.Book;
import com.senla.library.util.ArrayHandler;

public class BookRepository implements IBookRepository{
	
	private static BookRepository instance;
	private static final String FILE_PATH = "data/book.txt";
	private TextFileWorker textFileWorker;
	private List<IBook> bookList;

	private BookRepository() {
		textFileWorker = new TextFileWorker(FILE_PATH);
		bookList = new ArrayList<>();		
		readData();
	}
	
	public static BookRepository getInstance() throws NoSuchIdException {
		if (instance == null)
			instance = new BookRepository();
		return instance;
	}

	@Override
	public void addBook(IBook book) {
		bookList.add(book);
	}

	public IBook getBook(int id) throws NoSuchIdException {
		return ArrayHandler.getElementById(id, bookList);
	}

	public List<IBook> getBooks() {
		return bookList;
	}

	public void readData() {
		for (String book : textFileWorker.readFromFile())
			addBook(new Book(book.split("  ")));
	}

	public void saveData() {
		textFileWorker.writeToFile(ArrayHandler.getStringArray(bookList));		
	}

}
