package com.senla.library.api.facade;

import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.IRequest;
import com.senla.library.api.comparator.book.SortBookQueryType;
import com.senla.library.api.comparator.book.SortBookType;
import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.exception.NoSuchIdException;

public interface ILibraryManager {
	
	public ExecutionType addBook(IBook book) throws NoSuchIdException;
	public ExecutionType writeOffBook(int id) throws NoSuchIdException;
	public List<IBook> showBooks(SortBookType type);
	public List<IBook> showBookQuery(SortBookQueryType type);
	public List<IBook> showUnsoldBooks() throws NoSuchIdException;
	public String showBookDescription(int bookId) throws NoSuchIdException;
	public ExecutionType addOrder(IOrder order);
	public ExecutionType addBookToOrder(int id, int BookId) throws NoSuchIdException;
	public ExecutionType completeOrder(int id) throws NoSuchIdException;
	public ExecutionType cancelOrder(int id) throws NoSuchIdException;
	public String showOrderDetails(int orderId) throws NoSuchIdException;
	public List<IOrder> showOrders(SortOrderType type);
	public List<IOrder> showOrders(Date dateBefore, Date dateAfter, SortOrderType type);
	public double showTotalIncome(Date dateBefore, Date dateAfter);
	public int showCompletedOrderQuantity(Date dateBefore, Date dateAfter);
	public ExecutionType addRequest(IRequest request) throws NoSuchIdException;
	public ExecutionType exitProgram();
		
}
