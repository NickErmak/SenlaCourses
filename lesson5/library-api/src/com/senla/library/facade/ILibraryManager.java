package com.senla.library.facade;

import java.util.Date;
import java.util.List;

import com.senla.library.bean.IBook;
import com.senla.library.bean.IOrder;
import com.senla.library.bean.IRequest;
import com.senla.library.enums.SortBookQueryType;
import com.senla.library.enums.SortBookType;
import com.senla.library.enums.SortOrderType;

public interface ILibraryManager {
	
	public void addBook(IBook book);
	public void writeOffBook(int bookId);
	public List<IBook> showBooks(SortBookType type);
	public List<IBook> showBookQuery(SortBookQueryType type);
	public List<IBook> showUnsoldBooks();
	public String showBookDescription(int bookId);
	public void addOrder(IOrder order);
	public void addBookToOrder(int OrderId, int BookId);
	public void completeOrder(int orderId);
	public void cancelOrder(IOrder order);
	public String showOrderDetails(int orderId);
	public List<IOrder> showOrders(SortOrderType type);
	public List<IOrder> showOrders(Date dateBefore, Date dateAfter, SortOrderType type);
	public double showTotalIncome(Date dateBefore, Date dateAfter);
	public int showCompletedOrderQuantity(Date dateBefore, Date dateAfter);
	public void addRequest(IRequest request);
	public void exitProgram();
		
}
