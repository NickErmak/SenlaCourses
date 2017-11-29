package com.senla.library.api.comparator.book;

import java.util.Comparator;

import com.senla.library.api.bean.IBook;

public class BookByPriceComparator implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {
		if (book1 != null && book2 != null)
			return Double.compare(book1.getPrice(), book2.getPrice());
		else
			return 0;
	}

}
