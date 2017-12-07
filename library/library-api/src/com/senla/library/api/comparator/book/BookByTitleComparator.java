package com.senla.library.api.comparator.book;

import java.util.Comparator;

import com.senla.library.api.bean.IBook;

public class BookByTitleComparator implements Comparator<IBook>{
	
	@Override
	public int compare(IBook book1, IBook book2) {
		if (book1 != null && book2 != null)
			return book1.getTitle().compareTo(book2.getTitle());
		else
			return 0;
	}
}
