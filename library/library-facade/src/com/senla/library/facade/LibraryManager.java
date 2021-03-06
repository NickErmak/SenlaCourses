package com.senla.library.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.bean.IRequest;
import com.senla.library.api.bean.Status;
import com.senla.library.api.comparator.book.BookByOnStockComparator;
import com.senla.library.api.comparator.book.BookByPriceComparator;
import com.senla.library.api.comparator.book.BookByPublicationDateComparator;
import com.senla.library.api.comparator.book.BookByQuery;
import com.senla.library.api.comparator.book.BookByTitleComparator;
import com.senla.library.api.comparator.book.SortBookQueryType;
import com.senla.library.api.comparator.book.SortBookType;
import com.senla.library.api.comparator.order.OrderByDateComparator;
import com.senla.library.api.comparator.order.OrderByPriceComparator;
import com.senla.library.api.comparator.order.OrderByStatusComparator;
import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.facade.ExecutionType;
import com.senla.library.api.facade.ILibraryManager;
import com.senla.library.manager.BookManager;
import com.senla.library.manager.OrderBookManager;
import com.senla.library.manager.OrderManager;
import com.senla.library.manager.RequestManager;

public class LibraryManager implements ILibraryManager {
	
	private static ILibraryManager instance;
	private BookManager bookManager;
	private OrderManager orderManager;
	private RequestManager requestManager;
	private OrderBookManager orderBookManager;

	private LibraryManager() throws NoSuchIdException {		
		bookManager = new BookManager();
		orderManager = new OrderManager();
		requestManager = new RequestManager();
		orderBookManager = new OrderBookManager();
		refreshRelation();
	}

	public static ILibraryManager getInstance() throws NoSuchIdException {
		if (instance == null)
			instance = new LibraryManager();
		return instance;
	}
	
	public ExecutionType addBook(IBook book) throws NoSuchIdException {
		bookManager.addBook(book);
		IRequest request = requestManager.getRequest(book.getRequestId());
		if (request != null)
			requestManager.completeRequest(request);
		return ExecutionType.SUCCESS;
	}

	public ExecutionType writeOffBook(int bookId) throws NoSuchIdException {
		bookManager.writeOffBook(bookId);
		return ExecutionType.SUCCESS;
	}

	public List<IBook> showBooks(SortBookType type) {
		switch (type) {
		case ALPHABETICALLY:
			return bookManager.sortBookList(new BookByTitleComparator());
		case BY_PRICE:
			return bookManager.sortBookList(new BookByPriceComparator());
		case BY_PUBLICATION_DATE:
			return bookManager.sortBookList(new BookByPublicationDateComparator());
		case BY_STOCK:
			return bookManager.sortBookList(new BookByOnStockComparator());
		default:
			return null;
		}
	}

	public List<IBook> showBookQuery(SortBookQueryType type) {
		switch (type) {
		case ALPHABETICALLY:
			return bookManager.sortBookList(new BookByTitleComparator());
		case BY_QUANTITY:
			return bookManager.sortBookList(new BookByQuery());
		default:
			return null;
		}
	}

	public List<IBook> showUnsoldBooks() throws NoSuchIdException {
		List<IBook> unsoldBooks = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -6);
		for (IBook book : bookManager.getBooks()) {
			IRequest request = requestManager.getRequest(book.getRequestId());
			if (request != null && request.getDate() != null && request.getDate().before(calendar.getTime()))
				unsoldBooks.add(book);
		}
		return unsoldBooks;
	}

	public String showBookDescription(int bookId) throws NoSuchIdException {
		return bookManager.getBook(bookId).getDescription();
	}

	public ExecutionType addOrder(IOrder order) {
		orderManager.addOrder(order);
		return ExecutionType.SUCCESS;
	}

	public ExecutionType addBookToOrder(int orderId, int bookId) throws NoSuchIdException {		
		createRelation(orderBookManager.addOrderBookRelation(orderId, bookId));
		return ExecutionType.SUCCESS;
	}

	private void refreshRelation() throws NoSuchIdException {
		for (IOrderBookRelation relation : orderBookManager.getRelations())
			if (relation != null)
				createRelation(relation);
	}

	private void createRelation(IOrderBookRelation relation) throws NoSuchIdException {
		orderManager.addOrderBookRelation(relation, bookManager.getBook(relation.getBookId()).getPrice());
		bookManager.addOrderBookRelation(relation);
	}

	public ExecutionType completeOrder(int orderId) throws NoSuchIdException {
		List<IOrderBookRelation> relations = orderManager.getOrder(orderId).getOrderBookList();
		if (bookManager.isBookOnStock(relations)) {
			orderManager.completeOrder(orderId);
			bookManager.writeOffBook(relations);
			return ExecutionType.SUCCESS;
		}
		else return ExecutionType.ERROR; 
	}

	public ExecutionType cancelOrder(int id) throws NoSuchIdException {
		orderManager.cancelOrder(id);
		return ExecutionType.SUCCESS;
	}

	public String showOrderDetails(int orderId) throws NoSuchIdException {
		IOrder order = orderManager.getOrder(orderId);
		return order.toString() + order.getOrderBookList();
	}

	public List<IOrder> showOrders(SortOrderType type) {
		return sortOrder(type);		
	}

	public List<IOrder> showOrders(Date dateAfter, Date dateBefore, SortOrderType type) {
		List<IOrder> orders = new ArrayList<>();
			for (IOrder order : sortOrder(type)) {
				if (order.getStatus() == Status.COMPLETED)
					if (order.getDate().before(dateBefore) && order.getDate().after(dateAfter))
						orders.add(order);
			}
		return orders;
	}
		
	private List<IOrder> sortOrder(SortOrderType type) {
		switch (type) {
		case BY_EXECUTION_DATE:
			return orderManager.sortOrderList(new OrderByDateComparator());			
		case BY_PRICE:
			return orderManager.sortOrderList(new OrderByPriceComparator());			
		case BY_STATUS:
			return orderManager.sortOrderList(new OrderByStatusComparator());
		default:
			return null;
		}
	}

	public int showCompletedOrderQuantity(Date dateBefore, Date dateAfter) {
		return orderManager.getCompletedOrderQuantity(dateBefore, dateAfter);
	}

	public double showTotalIncome(Date dateBefore, Date dateAfter) {
		return orderManager.getTotalIncome(dateBefore, dateAfter);
	}

	public ExecutionType addRequest(IRequest request) throws NoSuchIdException {
		requestManager.addRequest(request);
		bookManager.setRequest(request);
		return ExecutionType.SUCCESS;
	}

	public ExecutionType exitProgram() {
		bookManager.save();
		orderManager.save();
		requestManager.save();
		orderBookManager.save();
		return ExecutionType.SUCCESS;
	}
}
