package com.senla.library.manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.senla.library.entity.Book;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.entity.Request;
import com.senla.library.repository.BookRepository;

public class BookManager {

	private final BookRepository bookRep;

	public BookManager(String filePath) {
		bookRep = new BookRepository(filePath);
	}

	public void addBook(Book book) {
		bookRep.addBook(book);
		book.setOnStock(true);
	}

	public Book getBook(int bookId) {
		return (Book) bookRep.getBook(bookId);
	}

	public List<Book> getBooks() {
		return bookRep.getBooks();
	}

	public void writeOffBook(int bookId) {
		getBook(bookId).setOnStock(false);
	}

	public void writeOffBook(List<OrderBookRelation> relationList) {
		for (int i = 0; i < relationList.size(); i++)
			getBook(relationList.get(i).getBookId()).setOnStock(false);
	}

	public boolean isBookOnStock(List<OrderBookRelation> relationList) {
		for (OrderBookRelation relation : relationList)
			if (!getBook(relation.getBookId()).isOnStock())
				return false;
		return true;
	}

	public void setRequest(Request request) {
		getBook(request.getBookId()).setRequestId(request.getId());
	}

	public List<Book> sortBookList(Comparator<Book> comparator) {
		Collections.sort(getBooks(), comparator);
		return getBooks();
	}

	public void addOrderBookRelation(OrderBookRelation relation) {
		getBook(relation.getBookId()).getOrderBookList().add(relation);
	}

	public void save() {
		bookRep.saveData();
	}
}
