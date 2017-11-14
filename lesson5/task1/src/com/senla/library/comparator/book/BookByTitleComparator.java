package com.senla.library.comparator.book;

import java.util.Comparator;

import com.senla.library.entity.Book;

public class BookByTitleComparator implements Comparator<Book> {

	@Override
	public int compare(Book book1, Book book2) {
		if (book1 != null && book2 != null)
			return book1.getTitle().compareTo(book2.getTitle());
		else
			return 0;
	}

}
