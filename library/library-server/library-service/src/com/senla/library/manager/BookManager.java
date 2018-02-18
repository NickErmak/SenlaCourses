package com.senla.library.manager;

import static com.senla.library.csv.CSVHandler.CSVFileReader.read;
import static com.senla.library.csv.CSVHandler.CSVFileWriter.write;

import java.util.Iterator;
import java.util.List;

import com.senla.library.api.bean.BookStatus;
import com.senla.library.api.bean.IBook;
import com.senla.library.api.dao.IBookDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.dao.entityDAO.DaoShell;
import com.senla.library.dao.entityDAO.GenericDAO;
import com.senla.library.entity.Book;

public class BookManager {
	private final IBookDAO bookDAO;

	public BookManager(String filePath) {
		bookDAO = DaoShell.getBookDAO();
	}

	public void addBook(IBook book) {
		bookDAO.add(book);
	}

	public IBook getBook(int bookId) throws NoSuchIdException {
		return bookDAO.get(bookId);
	}

	public void updateBook(IBook book) {
		bookDAO.update(book);
	}

	public void writeOffBook(IBook book) throws NoSuchIdException {
		book.setStatus(BookStatus.SOLD);
		updateBook(book);
	}

	@SuppressWarnings("unchecked")
	public List<IBook> getBooks() {
		return (List<IBook>) bookDAO.getAll();
	}

	@SuppressWarnings("unchecked")
	public List<IBook> getBooks(SortingCriteria sortingCriteria) {
		return (List<IBook>) bookDAO.getAll(sortingCriteria);
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
					updateBook(book);
				}
			} catch (NoSuchIdException e) {
				addBook(bookCSV);
			}
		}
	}
	
	public void close() {
		bookDAO.close();
	}
}
