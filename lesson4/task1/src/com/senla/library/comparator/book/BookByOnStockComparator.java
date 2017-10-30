package com.senla.library.comparator.book;

import java.util.Comparator;

import com.senla.library.entity.Book;

public class BookByOnStockComparator implements Comparator<Book> {

	@Override
	public int compare(Book book1, Book book2) {
		if (book1 != null && book2 != null)
			return Boolean.compare(book1.isOnStock(), book2.isOnStock());
		else
			return 0;
	}

}
