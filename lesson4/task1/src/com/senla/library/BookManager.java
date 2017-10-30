package com.senla.library;

import java.util.Arrays;
import java.util.Comparator;

import com.senla.library.entity.Book;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.entity.Request;
import com.senla.library.repository.BookRepository;
import com.senla.library.util.Printer;

public class BookManager {

	private static final String BOOK_ON_STOCK_ERROR = "Book is not at stock. Make a request! book id = ";

	private final BookRepository bookRep;

	public BookManager(int bookMax) {
		bookRep = new BookRepository(bookMax);
	}

	public void addBook(Book book) {
		if (getBook(book.getId()) == null)
			bookRep.addBook(book);
		book.setOnStock(true);
	}

	public Book getBook(int bookId) {
		return bookRep.getBook(bookId);
	}
	public Book[] getBookList() {
		return bookRep.getBookList();
	}

	public void writeOffBook(int bookId) {
		getBook(bookId).setOnStock(false);
	}

	public void writeOffBook(OrderBookRelation[] relationList) {
		for (int i = 0; i < relationList.length; i++)
			if (relationList[i] != null)
				relationList[i].getBook().setOnStock(false);
	}
	
	public void showBadSellingBook() {
		Printer.printBadSellBook(getBookList());
	}

	public boolean isBookOnStock(OrderBookRelation[] relationList) {
		for (OrderBookRelation relation : relationList)
			if (relation != null && !relation.getBook().isOnStock()) {
				Printer.print(BOOK_ON_STOCK_ERROR + relation.getBook().getId());
				return false;
			}
		return true;
	}

	public void setRequest(Request request) {
		getBook(request.getBookId()).setRequest(request);
	}

	public Book[] sortBookList(Comparator<Book> comparator) {
		Arrays.sort(getBookList(), comparator);
		return getBookList();
	}
	
	public void addOrderBookRelation(OrderBookRelation relation) {
		relation.getBook().addOrderBookRelation(relation);
	}

	public void showBookDescription(int bookId) {
		Printer.print(getBook(bookId).getDescription());
	}
	
	public void save() {
		bookRep.save();
	}
}
