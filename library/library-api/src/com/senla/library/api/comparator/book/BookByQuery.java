package com.senla.library.api.comparator.book;

import java.util.Comparator;

import com.senla.library.api.bean.IBook;

public class BookByQuery implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {
		if (book1 != null && book2 != null)
			return Integer.compare(book1.getOrderBookList().size(),
					book2.getOrderBookList().size());
		else
			return 0;
	}
}
