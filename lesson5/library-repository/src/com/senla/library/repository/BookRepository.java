package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.bean.IBook;
import com.senla.library.entity.Book;
import com.senla.library.util.ArrayHandler;

public class BookRepository implements IBookRepository{
	
	private TextFileWorker textFileWorker;
	private List<IBook> bookList;

	public BookRepository(String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		bookList = new ArrayList<>();		
		readData();
	}

	@Override
	public void addBook(IBook book) {
		bookList.add(book);
	}

	public IBook getBook(int id) {
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
