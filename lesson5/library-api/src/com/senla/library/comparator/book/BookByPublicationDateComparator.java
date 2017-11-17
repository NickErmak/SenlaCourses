package com.senla.library.comparator.book;

import java.util.Comparator;

import com.senla.library.bean.IBook;

public class BookByPublicationDateComparator implements Comparator<IBook> {

	@Override
	public int compare(IBook book1, IBook book2) {
		if (book1 != null && book2 != null)
			return book1.getPublicationDate().compareTo(book2.getPublicationDate());
		else
			return 0;
	}

}
