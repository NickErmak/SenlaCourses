package com.senla.library.comparator.book;

import java.util.Comparator;
import com.senla.library.entity.Book;
import com.senla.library.util.ArrayHandler;

public class BookByQuery implements Comparator<Book> {

	@Override
	public int compare(Book book1, Book book2) {
		if (book1 != null && book2 != null)
			return Integer.compare(ArrayHandler.getElementQuantity(book1.getOrderBookList()),
					ArrayHandler.getElementQuantity(book2.getOrderBookList()));
		else
			return 0;
	}

}
