package com.senla.library.manager;

import static com.senla.library.csv.CSVHandler.CSVFileReader.read;
import static com.senla.library.csv.CSVHandler.CSVFileWriter.write;
import static com.senla.library.util.DateConverter.minusMonth;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.senla.library.api.bean.BookStatus;
import com.senla.library.api.bean.IBook;
import com.senla.library.api.dao.IBookDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.dao.entityDAO.DaoShell;
import com.senla.library.entity.Book;

public class BookManager {
	private final IBookDAO bookDAO;

	public BookManager(String filePath) {
		bookDAO = DaoShell.getBookDAO();
	}

	public void addBookToStorage(IBook book) throws Exception {
		book.setStatus(BookStatus.ON_STORAGE);
		updateBook(book);
	}

	public void charterBook(IBook book) throws Exception {
		bookDAO.add(book);
	}

	public IBook getBook(int id) throws Exception {
		return bookDAO.getBook(id);
	}

	public void updateBook(IBook book) throws Exception {
		bookDAO.update(book);
	}

	public void writeOffBook(IBook book) throws Exception {
		book.setStatus(BookStatus.SOLD);
		updateBook(book);
	}

	public List<IBook> getBooks() throws Exception {
		return bookDAO.getBooks();
	}

	public List<IBook> getBooks(SortingCriteria sortingCriteria) throws Exception {
		return bookDAO.getBooks(sortingCriteria);
	}

	public List<IBook> getUnsoldBooks(SortingCriteria sortingCriteria, int unsoldMonthAmount) throws Exception {
		Field date = Book.class.getDeclaredField("arrivalDate");
		return bookDAO.getBooks(sortingCriteria, date, minusMonth(unsoldMonthAmount));
	}

	public void exportCSV(String filePath) throws Exception {
		List<IBook> books = getBooks();
		if (!books.isEmpty()) {
			write(getBooks(), filePath);
		}
	}

	public void importCSV(String filePath) throws Exception {
		Iterator<Book> iteratorCSV = read(Book.class, filePath).iterator();
		Map<Integer, IBook> mappedBooks = getMappedBooks();
		while (iteratorCSV.hasNext()) {
			IBook bookCSV = iteratorCSV.next();
			if (mappedBooks.containsKey(bookCSV.getId())) {
				updateBook(bookCSV);
			} else {
				bookDAO.add(bookCSV);
			}
		}
	}

	private Map<Integer, IBook> getMappedBooks() throws Exception {
		Map<Integer, IBook> mappedBooks = new HashMap<>();
		Iterator<IBook> books = getBooks().iterator();
		while (books.hasNext()) {
			IBook book = books.next();
			mappedBooks.put(book.getId(), book);
		}
		return mappedBooks;
	}

	public void exit() throws Exception {
		bookDAO.exit();
	}
}
