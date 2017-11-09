package com.senla.library.repository;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.Book;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.FileWorker;

public class BookRepository {

	public static final int ID_LAST_DIGIT = 1;
	public static final int MAX_ORDER = 10;
	private TextFileWorker textFileWorker;
	private Book[] bookList;

	public BookRepository(int bookMax, String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		bookList = new Book[bookMax];
		readData();
	}

	public void addBook(Book book) {
		bookList[ArrayHandler.getFreeCellIndex(bookList)] = book;		
	}

	public Book getBook(int bookId) {
		return (Book) ArrayHandler.getElementById(bookId, bookList);		
	}

	public Book[] getBooks() {
		return bookList;
	}

	public void readData() {
		FileWorker.readData(textFileWorker, bookList, new Book());
	}

	public void saveData() {
		FileWorker.saveData(textFileWorker, bookList);
	}

}
