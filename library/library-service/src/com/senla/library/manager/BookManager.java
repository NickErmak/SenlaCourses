package com.senla.library.manager;

import static com.senla.library.util.CSVWorker.loadCSV;
import static com.senla.library.util.CSVWorker.saveCSV;
import static com.senla.library.util.CollectionHandler.parseToStringWithHeader;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.bean.IRequest;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.entity.Book;
import com.senla.library.repository.BookRepository;

public class BookManager {
	private final static String[] CSV_HEAD = { "id", "title", "publicationDate", "price", "description", "request Id",
			"onStock" };
	private final BookRepository bookRepository;

	public BookManager(String filePath) {
		bookRepository = BookRepository.getInstance();
		bookRepository.readData(filePath);
	}

	public void addBook(IBook book) {
		bookRepository.addBook(book);
	}

	public IBook getBook(int bookId) throws NoSuchIdException {
		return bookRepository.getBook(bookId);
	}

	public List<IBook> getBooks() {
		return bookRepository.getBooks();
	}

	public void writeOffBook(int bookId) throws NoSuchIdException {
		getBook(bookId).setOnStock(false);
	}

	public void writeOffBook(List<IOrderBookRelation> relationList) throws NoSuchIdException {
		for (int i = 0; i < relationList.size(); i++) {
			getBook(relationList.get(i).getBookId()).setOnStock(false);
		}
	}

	public boolean isBookOnStock(List<IOrderBookRelation> relationList) throws NoSuchIdException {
		for (IOrderBookRelation relation : relationList) {
			if (!getBook(relation.getBookId()).isOnStock()) {
				return false;
			}
		}
		return true;
	}

	public void setRequest(IRequest request) throws NoSuchIdException {
		getBook(request.getBookId()).setRequestId(request.getId());
	}

	public List<IBook> sortBookList(Comparator<IBook> comparator) {
		Collections.sort(getBooks(), comparator);
		return getBooks();
	}

	public void addOrderBookRelation(IOrderBookRelation relation) throws NoSuchIdException {
		getBook(relation.getBookId()).getOrderBookList().add(relation);
	}

	public void exportCSV(String filePath) {
		saveCSV(filePath, parseToStringWithHeader(getBooks(), CSV_HEAD));
	}

	public void importCSV(String filePath) {
		ListIterator<String[]> listIterator = loadCSV(filePath).listIterator(1);
		while (listIterator.hasNext()) {
			IBook bookCSV = new Book(listIterator.next());
			try {
				IBook book = getBook(bookCSV.getId());
				if (book != null) {
					book = bookCSV;
				}
			} catch (NoSuchIdException e) {
				addBook(bookCSV);
			}
		}
	}

	public void saveData(String filePath) {
		bookRepository.saveData(filePath);
	}
}
