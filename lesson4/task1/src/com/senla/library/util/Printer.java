package com.senla.library.util;

import java.util.Calendar;
import java.util.Date;

import com.senla.library.entity.Book;
import com.senla.library.entity.EntityId;
import com.senla.library.entity.Order;
import com.senla.library.entity.Status;

public class Printer {

	public static void print(String message) {
		System.out.println(message);
	}

	public static void print(EntityId entity) {
		if (entity != null)
			System.out.println(entity);
	}

	public static void print(EntityId[] array) {
		for (EntityId entity : array) {
			print(entity);
		}
	}

	public static void print(Order[] orderList, Date dateBefore, Date dateAfter) {
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
				print(book.getTitle() + " - " + book.getOrderBookRelationQuantity() + " pcs ");
	}

	public static void printBadSellBook(Book[] bookList) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -6);
		for (Book book : bookList)
			if (book != null)
				if (book.getRequest().getDate().before(calendar.getTime()))
					print(book);
	}

}
