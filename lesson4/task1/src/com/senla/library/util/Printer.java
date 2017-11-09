package com.senla.library.util;

import java.util.Date;

import com.senla.library.entity.Book;
import com.senla.library.entity.Entity;
import com.senla.library.entity.Order;
import com.senla.library.entity.Status;

public class Printer {

	public static void print(String message) {
		System.out.println(message);
	}

	public static void print(Entity entity) {
		if (entity != null)
			System.out.println(entity);
	}

	public static void print(Entity[] array) {
		for (Entity entity : array) {
			print(entity);
		}
	}

	public static void print(Order[] orderList, Date dateAfter, Date dateBefore) {
		for (Order order : orderList) {
			if (order != null)
				if (order.getStatus() == Status.COMPLETED)
					if (order.getDate().before(dateBefore) && order.getDate().after(dateAfter))
						print(order);
		}
	}

	public static void printQuery(Book[] bookList) {
		for (Book book : bookList)
			if (book != null)
				print(book.getTitle() + " - " + ArrayHandler.getElementQuantity(book.getOrderBookList()) + " pcs ");
	}
	
}
