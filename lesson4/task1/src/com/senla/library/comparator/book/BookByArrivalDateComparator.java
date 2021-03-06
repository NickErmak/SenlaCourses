package com.senla.library.comparator.book;

import java.util.Comparator;

import com.senla.library.entity.Book;

public class BookByArrivalDateComparator implements Comparator<Book>{

	public BookByArrivalDateComparator() {
		
	}
	
	@Override
	public int compare(Book book1, Book book2) {
		if (book1 != null && book2 != null)
			return book1.getPublicationDate().compareTo(book2.getPublicationDate());
		else
			return 0;
	}


}
