package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.annotation.di.Singleton;
import com.senla.library.api.bean.IBook;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IBookRepository;
import com.senla.library.util.CollectionHandler;
import com.senla.library.util.FileWorker;

@Singleton
public class BookRepository implements IBookRepository {
	private static IBookRepository instance;
	private List<IBook> books;

	private BookRepository() {
		books = new ArrayList<>();
		
	}

	public static IBookRepository getInstance() {
		if (instance == null) {
			instance = new BookRepository();
		}
		return instance;
	} 

	@Override
	public void addBook(IBook book) {
		books.add(book);
	}

	@Override
	public IBook getBook(int id) throws NoSuchIdException {
		return CollectionHandler.getElementById(id, books);
	}
	
	@Override
	public void refreshBook(IBook deprecatedBook, IBook refreshedBook) {
		books.remove(deprecatedBook);
		books.add(refreshedBook);
	}

	@Override
	public List<IBook> getBooks() {
		return books;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readData(String filePath) {
		Object fileData = FileWorker.read(filePath);
		if (fileData != null) {
			books = (List<IBook>) fileData;
		}
	}

	@Override
	public void saveData(String filePath) {
		FileWorker.save(books, filePath);
	}
}
