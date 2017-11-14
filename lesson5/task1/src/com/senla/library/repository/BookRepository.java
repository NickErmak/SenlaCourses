package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.Book;
import com.senla.library.util.ArrayHandler;

public class BookRepository{

	public static final int ID_LAST_DIGIT = 1;
	private TextFileWorker textFileWorker;
	private List<Book> bookList;

	public BookRepository(String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		bookList = new ArrayList<>();		
		readData();
	}

	public void addBook(Book book) {
		bookList.add(book);
	}

	public Book getBook(int id) {
		return ArrayHandler.getElementById(id, bookList);
	}

	public List<Book> getBooks() {
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
