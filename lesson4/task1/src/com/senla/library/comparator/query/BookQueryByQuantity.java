package com.senla.library.comparator.query;

import java.util.Comparator;
import com.senla.library.entity.Book;

public class BookQueryByQuantity implements Comparator<Book>{

	@Override
	public int compare(Book book1, Book book2) {
		return Integer.compare(book1.getOrderBookRelationQuantity(), book2.getOrderBookRelationQuantity());
	}

}
