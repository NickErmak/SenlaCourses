package com.senla.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.senla.library.comparator.book.SortBookType;
import com.senla.library.entity.Book;
import com.senla.library.entity.Order;
import com.senla.library.entity.Request;

public class Test {

	public static void main(String args[]) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date date1 = format.parse("10/12/1945");
		Date date2 = format.parse("10/12/1912");
		Date date3 = format.parse("10/12/1944");

		Book book1 = new Book("1984", date1, 10, "description1");
		Book book2 = new Book("Alice WonderLand", date2, 15, "description2");
		Book book3 = new Book("Catcher in the rye", date3, 8, "description3");

		LibraryManager libManager = new LibraryManager(100, 100, 100);

		libManager.addBook(book1);
		libManager.addBook(book2);
		libManager.addBook(book3);
		libManager.showBookList(SortBookType.BY_PRICE);

		Order order1 = new Order("Order1");
		Order order2 = new Order("Order2");
		Order order3 = new Order("Order3");
		libManager.addOrder(order1);
		libManager.addOrder(order2);
		libManager.addOrder(order3);

		libManager.showOrderDetails(order1.getId());

		libManager.writeOffBook(book2.getId());

		libManager.addBookToOrder(order1.getId(), book1.getId());
		libManager.addBookToOrder(order1.getId(), book2.getId());
		libManager.showOrderDetails(order1.getId());
		libManager.completeOrder(order1.getId());

		Request request1 = new Request(book2.getId());
		libManager.addRequest(request1);
		libManager.addBook(book2);

		libManager.completeOrder(order1.getId());
		libManager.showOrderDetails(order1.getId());

		libManager.exitProgram();

	}
}
