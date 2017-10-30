package com.senla.library.comparator.query;

import java.util.Comparator;
import com.senla.library.entity.Book;

public class BookQueryByTitle implements Comparator<Book>{

	@Override
	public int compare(Book book1, Book book2) {
		return book1.getTitle().compareTo(book2.getTitle());
	}
	
}
