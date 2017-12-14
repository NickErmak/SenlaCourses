package com.senla.library.manager;

import static com.senla.library.csv.CSVHandler.CSVFileReader.read;
import static com.senla.library.csv.CSVHandler.CSVFileWriter.write;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.bean.IRequest;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.api.repository.IBookRepository;
import com.senla.library.entity.Book;
import com.senla.library.repository.RepositoryShell;

public class BookManager {
	private final IBookRepository bookRepository;

	public BookManager(String filePath) {
		bookRepository = RepositoryShell.getBookRepository();
		bookRepository.readData(filePath);
	}

	public void addBook(IBook book) {
		bookRepository.addBook(book);
	}

	public IBook getBook(int bookId) throws NoSuchIdException {
		return bookRepository.getBook(bookId);
	}

	public void refreshBook(IBook deprecatedBook, IBook refreshedBook) {
		bookRepository.refreshBook(deprecatedBook, refreshedBook);
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

	public void exportCSV(String filePath) throws NonParseableException {
		List<IBook> books = getBooks();
		if (!books.isEmpty()) {
			write(getBooks(), filePath);
		}
	}

	public void importCSV(String filePath) throws NonParseableException {
		Iterator<Book> iteratorCSV = read(Book.class, filePath).iterator();
		while (iteratorCSV.hasNext()) {
			IBook bookCSV = iteratorCSV.next();
			try {
				IBook book = getBook(bookCSV.getId());
				if (book != null) {
					refreshBook(book, bookCSV);
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
