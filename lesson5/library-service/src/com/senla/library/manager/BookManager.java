package com.senla.library.manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.senla.library.bean.IBook;
import com.senla.library.bean.IOrderBookRelation;
import com.senla.library.bean.IRequest;
import com.senla.library.repository.BookRepository;

public class BookManager {

	private final BookRepository bookRepository;

	public BookManager(String filePath) {
		bookRepository = new BookRepository(filePath);
	}

	public void addBook(IBook book) {
		bookRepository.addBook(book);
		book.setOnStock(true);
	}

	public IBook getBook(int bookId) {
		return (IBook) bookRepository.getBook(bookId);
	}

	public List<IBook> getBooks() {
		return bookRepository.getBooks();
	}

	public void writeOffBook(int bookId) {
		getBook(bookId).setOnStock(false);
	}

	public void writeOffBook(List<IOrderBookRelation> relationList) {
		for (int i = 0; i < relationList.size(); i++)
			getBook(relationList.get(i).getBookId()).setOnStock(false);
	}

	public boolean isBookOnStock(List<IOrderBookRelation> relationList) {
		for (IOrderBookRelation relation : relationList)
			if (!getBook(relation.getBookId()).isOnStock())
				return false;
		return true;
	}

	public void setRequest(IRequest request) {
		getBook(request.getBookId()).setRequestId(request.getId());
	}

	public List<IBook> sortBookList(Comparator<IBook> comparator) {
		Collections.sort(getBooks(), comparator);
		return getBooks();
	}

	public void addOrderBookRelation(IOrderBookRelation relation) {
		getBook(relation.getBookId()).getOrderBookList().add(relation);
	}

	public void save() {
		bookRepository.saveData();
	}
}
