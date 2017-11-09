package com.senla.library.manager;

import java.util.Arrays;
import java.util.Comparator;

import com.senla.library.entity.Book;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.entity.Request;
import com.senla.library.repository.BookRepository;
import com.senla.library.util.ArrayHandler;

public class BookManager {	

	private final BookRepository bookRep;

	public BookManager(int bookMax, String filePath) {
		bookRep = new BookRepository(bookMax, filePath);
	}

	public void addBook(Book book) {		
		bookRep.addBook(book);		
		book.setOnStock(true);
	}

	public Book getBook(int bookId) {
		return bookRep.getBook(bookId);
	}

	public Book[] getBooks() {
		return bookRep.getBooks();
	}

	public void writeOffBook(int bookId) {
		getBook(bookId).setOnStock(false);
	}

	public void writeOffBook(OrderBookRelation[] relationList) {
		for (int i = 0; i < relationList.length; i++)
			if (relationList[i] != null)
				getBook(relationList[i].getBookId()).setOnStock(false);
	}
	
	public boolean isBookOnStock(OrderBookRelation[] relationList) {
		for (OrderBookRelation relation : relationList) 
			if (relation != null && !getBook(relation.getBookId()).isOnStock()) 				
				return false;			
		return true;
	}

	public void setRequest(Request request) {
		getBook(request.getBookId()).setRequestId(request.getId());
	}

	public Book[] sortBookList(Comparator<Book> comparator) {
		Arrays.sort(getBooks(), comparator);
		return getBooks();
	}

	public void addOrderBookRelation(OrderBookRelation relation) {
		OrderBookRelation[] relationList = getBook(relation.getBookId()).getOrderBookList();
		relationList[ArrayHandler.getFreeCellIndex(relationList)] = relation;
	}	

	public void save() {
		bookRep.saveData();
	}
}
