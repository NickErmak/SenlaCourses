package com.senla.library.comparator.book;

import java.util.Comparator;

import com.senla.library.entity.Book;

public class BookByQuery implements Comparator<Book> {

	@Override
	public int compare(Book book1, Book book2) {
		if (book1 != null && book2 != null)
			return Integer.compare(book1.getOrderBookList().size(),
					book2.getOrderBookList().size());
		else
			return 0;
	}

}
