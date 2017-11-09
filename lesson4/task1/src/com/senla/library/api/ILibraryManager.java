package com.senla.library.api;

import java.util.Date;

import com.senla.library.comparator.book.SortBookQueryType;
import com.senla.library.comparator.book.SortBookType;
import com.senla.library.comparator.order.SortOrderType;
import com.senla.library.entity.Book;
import com.senla.library.entity.Order;
import com.senla.library.entity.Request;

public interface ILibraryManager {
	
	public void addBook(Book book);
	public void writeOffBook(int bookId);
	public void showBooks(SortBookType type);
	public void showBookQuery(SortBookQueryType type);
	public void showUnsoldBooks();
	public void showBookDescription(int bookId);
	public void addOrder(Order order);
	public void addBookToOrder(int OrderId, int BookId);
	public void completeOrder(int orderId);
	public void cancelOrder(Order order);
	public void showOrderDetails(int orderId);
	public void showOrders(SortOrderType type);
	public void showOrders(Date dateBefore, Date dateAfter, SortOrderType type);
	public void showTotalIncome(Date dateBefore, Date dateAfter);
	public void showCompletedOrderQuantity(Date dateBefore, Date dateAfter);
	public void addRequest(Request request);
	public void exitProgram();
		
}
