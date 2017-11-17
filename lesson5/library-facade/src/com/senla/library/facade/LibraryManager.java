package com.senla.library.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.senla.library.bean.IBook;
import com.senla.library.bean.IOrder;
import com.senla.library.bean.IOrderBookRelation;
import com.senla.library.bean.IRequest;
import com.senla.library.comparator.book.BookByOnStockComparator;
import com.senla.library.comparator.book.BookByPriceComparator;
import com.senla.library.comparator.book.BookByPublicationDateComparator;
import com.senla.library.comparator.book.BookByQuery;
import com.senla.library.comparator.book.BookByTitleComparator;
import com.senla.library.comparator.order.OrderByDateComparator;
import com.senla.library.comparator.order.OrderByPriceComparator;
import com.senla.library.comparator.order.OrderByStatusComparator;
import com.senla.library.enums.SortBookQueryType;
import com.senla.library.enums.SortBookType;
import com.senla.library.enums.SortOrderType;
import com.senla.library.enums.Status;
import com.senla.library.manager.BookManager;
import com.senla.library.manager.OrderBookManager;
import com.senla.library.manager.OrderManager;
import com.senla.library.manager.RequestManager;

public class LibraryManager implements ILibraryManager {

	private final static String[] FILE_PATH_DEFAULT = { "data/book.txt", "data/order.txt", "data/request.txt",
			"data/relation.txt" };
	private BookManager bookManager;
	private OrderManager orderManager;
	private RequestManager requestManager;
	private OrderBookManager orderBookManager;

	public LibraryManager(String[] filePath) {
		if (filePath.length == 0)
			filePath = FILE_PATH_DEFAULT;
		bookManager = new BookManager(filePath[0]);
		orderManager = new OrderManager(filePath[1]);
		requestManager = new RequestManager(filePath[2]);
		orderBookManager = new OrderBookManager(filePath[3]);
		refreshRelation();
	}

	public void addBook(IBook book) {
		bookManager.addBook(book);
		IRequest request = requestManager.getRequest(book.getRequestId());
		if (request != null)
			requestManager.completeRequest(request);
	}

	public void writeOffBook(int bookId) {
		bookManager.writeOffBook(bookId);
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

	public List<IBook> showUnsoldBooks() {
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

	public String showBookDescription(int bookId) {
		return bookManager.getBook(bookId).toString();
	}

	public void addOrder(IOrder order) {
		orderManager.addOrder(order);
	}

	public void addBookToOrder(int orderId, int bookId) {		
		createRelation(orderBookManager.addOrderBookRelation(orderId, bookId));
	}

	private void refreshRelation() {
		for (IOrderBookRelation relation : orderBookManager.getRelations())
			if (relation != null)
				createRelation(relation);
	}

	private void createRelation(IOrderBookRelation relation) {
		orderManager.addOrderBookRelation(relation, bookManager.getBook(relation.getBookId()).getPrice());
		bookManager.addOrderBookRelation(relation);
	}

	public void completeOrder(int orderId) {
		List<IOrderBookRelation> relations = orderManager.getOrder(orderId).getOrderBookList();
		if (bookManager.isBookOnStock(relations)) {
			orderManager.completeOrder(orderId);
			bookManager.writeOffBook(relations);
		}
	}

	public void cancelOrder(IOrder order) {
		orderManager.cancelOrder(order);
	}

	public String showOrderDetails(int orderId) {
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

	public void addRequest(IRequest request) {
		requestManager.addRequest(request);
		bookManager.setRequest(request);
	}

	public void exitProgram() {
		bookManager.save();
		orderManager.save();
		requestManager.save();
		orderBookManager.save();
	}
}
