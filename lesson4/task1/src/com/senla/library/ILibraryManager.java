package com.senla.library;

import java.util.Date;

import com.senla.library.comparator.book.SortBookType;
import com.senla.library.comparator.order.SortOrderType;
import com.senla.library.comparator.query.SortQueryType;
import com.senla.library.entity.Book;
import com.senla.library.entity.Order;
import com.senla.library.entity.Request;

public interface ILibraryManager {
	
	public void addBook(Book book);
	public void writeOffBook(int bookId);
	public void showBookList(SortBookType type);
	public void showBookQuery(SortQueryType type);
	public void showBadSellingBook();
	public void showBookDescription(int bookId);
	public void addOrder(Order order);
	public void completeOrder(int orderId);
	public void cancelOrder(Order order);
	public void showOrderDetails(int orderId);
	public void showOrderList(SortOrderType type);
	public void showOrderList(Date dateBefore, Date dateAfter, SortOrderType type);
	public double getTotalIncome(Date dateBefore, Date dateAfter);
	public void showCompletedOrderQuantity(Date dateBefore, Date dateAfter);
	public void addRequest(Request request);
		
}
