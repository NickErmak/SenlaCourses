package com.senla.library.util;

import java.util.Date;
import java.util.List;

import com.senla.library.entity.Book;
import com.senla.library.entity.Entity;
import com.senla.library.entity.Order;
import com.senla.library.entity.Status;

public class Printer {

	public static void print(String message) {
		System.out.println(message);
	}

	public static void print(Entity entity) {
		System.out.println(entity);
	}

	public static void print(List<? extends Entity> list) {
		for (Entity entity : list) {
			print(entity);
		}
	}

	public static void print(List<Order> orderList, Date dateAfter, Date dateBefore) {
		for (Order order : orderList) {
			if (order.getStatus() == Status.COMPLETED)
				if (order.getDate().before(dateBefore) && order.getDate().after(dateAfter))
					print(order);
		}
	}

	public static void printQuery(List<Book> bookList) {
		for (Book book : bookList)
			print(book.getTitle() + " - " + book.getOrderBookList().size() + " pcs ");
	}
	
}
