package com.senla.library.api.facade;

import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.comparator.book.SortBookQueryType;
import com.senla.library.api.comparator.book.SortBookType;
import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.api.ui.ConsoleMessage;

public interface ILibraryManager {

	public ConsoleMessage addBook(IBook book) throws NoSuchIdException;

	public ConsoleMessage writeOffBook(int id) throws NoSuchIdException;

	public List<IBook> showBooks(SortBookType type);

	public List<IBook> showBookQuery(SortBookQueryType type);

	public List<IBook> showUnsoldBooks() throws NoSuchIdException;

	public String showBookDescription(String bookId) throws NoSuchIdException;

	public ConsoleMessage addOrder(IOrder order);

	public ConsoleMessage completeOrder(int id) throws NoSuchIdException;

	public ConsoleMessage cancelOrder(int id) throws NoSuchIdException;

	public String showOrderDetails(int orderId) throws NoSuchIdException;

	public List<IOrder> showOrders(SortOrderType type);

	public List<IOrder> showOrders(Date dateBefore, Date dateAfter, SortOrderType type);

	public double showTotalIncome(Date dateBefore, Date dateAfter);

	public int showCompletedOrderQuantity(Date dateBefore, Date dateAfter);

	public ConsoleMessage exitProgram();

	public ConsoleMessage cloneOrder(int id) throws NoSuchIdException;

	public ConsoleMessage exportCSVBook() throws NonParseableException;

	public ConsoleMessage importCSVBook() throws NonParseableException;

	public ConsoleMessage exportCSVOrder() throws NonParseableException;

	public ConsoleMessage importCSVOrder() throws NonParseableException;}
