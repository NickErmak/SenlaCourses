package com.senla.library.repository;

import java.text.ParseException;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.Book;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.DateConverter;

public class BookRepository {

	public static final int ID_LAST_DIGIT = 1;
	public static final int MAX_ORDER = 10;

	private final String FILE_PATH = "d:/book_file.txt";
	private TextFileWorker textFileWorker;
	private Book[] bookList;

	public BookRepository(int BookMax) {
		textFileWorker = new TextFileWorker(FILE_PATH);
		readBookFile();
	}

	public void addBook(Book book) {
		bookList[ArrayHandler.getFreeCellIndex(bookList)] = book;
	}

	public Book getBook(int bookId) {
		int index = ArrayHandler.getElementIndex(bookId, bookList);
		if (index != -1)
			return bookList[index];
		else
			return null;
	}

	public Book[] getBookList() {
		return bookList;
	}

	public void save() {
		textFileWorker.writeToFile(getBookStringArray());
	}

	public void readBookFile() {
		String[] bookArray = textFileWorker.readFromFile();
		bookList = new Book[bookArray.length + 5];
		for (int i = 0; i < bookArray.length; i++) {
			String[] bookToString = bookArray[i].split("%%");
			Book book;
			try {
				book = new Book(bookToString[0], DateConverter.stringToDate(bookToString[1]),
						new Double(bookToString[2]), bookToString[3]);
				book.setId(new Integer(bookToString[4]));
				book.setOnStock(new Boolean(bookToString[5]));
				book.setRequestId(bookToString[6]);
				book.setOrderBookRelationIdList(bookToString[7].split("id"));
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
				book = null;
			}
			bookList[i] = book;
		}
	}

	private String[] getBookStringArray() {
		String[] bookArray = new String[ArrayHandler.getElementQuantity(bookList)];
		for (int i = 0; i < bookArray.length; i++) {
			StringBuilder bookToString = new StringBuilder();
			bookToString.append(bookList[i].getTitle()).append("%%");
			bookToString.append(DateConverter.dateToString(bookList[i].getPublicationDate())).append("%%");
			bookToString.append(bookList[i].getPrice()).append("%%");
			bookToString.append(bookList[i].getDescription()).append("%%");
			bookToString.append(bookList[i].getId()).append("%%");
			bookToString.append(bookList[i].isOnStock()).append("%%");
			bookToString.append(bookList[i].getRequest()).append("%%");
			bookToString.append(bookList[i].getOrderBookRelationId());
			bookArray[i] = bookToString.toString();
		}
		return bookArray;
	}

}
