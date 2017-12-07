package com.senla.library.api.comparator.book;

import java.util.Comparator;

import com.senla.library.api.bean.IBook;

public class BookByOnStockComparator implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {
		if (book1 != null && book2 != null)
			return Boolean.compare(book2.isOnStock(), book1.isOnStock());
		else
			return 0;
	}
}
