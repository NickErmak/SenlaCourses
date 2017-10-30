package com.senla.library.comparator.book;

import java.util.Comparator;

import com.senla.library.entity.Book;

public class BookByPriceComparator implements Comparator<Book> {

	@Override
	public int compare(Book book1, Book book2) {
		if (book1 != null && book2 != null)
			return Double.compare(book1.getPrice(), book2.getPrice());
		else
			return 0;
	}

}
