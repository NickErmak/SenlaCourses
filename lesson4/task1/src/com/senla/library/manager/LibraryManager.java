package com.senla.library.manager;

import java.util.Calendar;
import java.util.Date;

import com.senla.library.api.ILibraryManager;
import com.senla.library.comparator.book.BookByOnStockComparator;
import com.senla.library.comparator.book.BookByPriceComparator;
import com.senla.library.comparator.book.BookByPublicationDateComparator;
import com.senla.library.comparator.book.BookByQuery;
import com.senla.library.comparator.book.BookByTitleComparator;
import com.senla.library.comparator.book.SortBookQueryType;
import com.senla.library.comparator.book.SortBookType;
import com.senla.library.comparator.order.OrderByDateComparator;
import com.senla.library.comparator.order.OrderByPriceComparator;
import com.senla.library.comparator.order.OrderByStatusComparator;
import com.senla.library.comparator.order.SortOrderType;
import com.senla.library.entity.Book;
import com.senla.library.entity.Order;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.entity.Request;
import com.senla.library.util.Printer;

public class LibraryManager implements ILibraryManager {

	private final static String[] FILE_PATH_DEFAULT = { "data/book.txt", "data/order.txt", "data/request.txt",
			"data/relation.txt" };
	private BookManager bookManager;
	private OrderManager orderManager;
	private RequestManager requestManager;
	private OrderBookManager orderBookManager;

	public LibraryManager(int maxBook, int maxOrder, int maxRequest, String[] filePath) {
		if (filePath.length == 0)
			filePath = FILE_PATH_DEFAULT;
		bookManager = new BookManager(maxBook, filePath[0]);
		orderManager = new OrderManager(maxOrder, filePath[1]);
		requestManager = new RequestManager(maxRequest, filePath[2]);
		orderBookManager = new OrderBookManager(maxBook, filePath[3]);
		refreshRelation();
	}

	public void addBook(Book book) {
		bookManager.addBook(book);
		Request request = requestManager.getRequest(book.getRequestId());
		if (request != null)
			requestManager.completeRequest(request);
	}

	public void writeOffBook(int bookId) {
		bookManager.writeOffBook(bookId);
	}

	public void showBooks(SortBookType type) {
		switch (type) {
		case ALPHABETICALLY:
			Printer.print(bookManager.sortBookList(new BookByTitleComparator()));
			break;
		case BY_PRICE:
			Printer.print(bookManager.sortBookList(new BookByPriceComparator()));
			break;
		case BY_PUBLICATION_DATE:
			Printer.print(bookManager.sortBookList(new BookByPublicationDateComparator()));
			break;
		case BY_STOCK:
			Printer.print(bookManager.sortBookList(new BookByOnStockComparator()));
			break;
		}
	}

	public void showBookQuery(SortBookQueryType type) {
		switch (type) {
		case ALPHABETICALLY:
			Printer.printQuery(bookManager.sortBookList(new BookByTitleComparator()));
			break;
		case BY_QUANTITY:
			Printer.printQuery(bookManager.sortBookList(new BookByQuery()));
			break;
		}
	}

	public void showUnsoldBooks() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -6);
		for (Book book : bookManager.getBooks())
			if (book != null) {
				Request request = requestManager.getRequest(book.getRequestId());
				if (request != null && request.getDate() != null && request.getDate().before(calendar.getTime()))
					Printer.print(book);
			}
	}

	public void showBookDescription(int bookId) {
		Printer.print(bookManager.getBook(bookId).getDescription());
	}

	public void addOrder(Order order) {
		orderManager.addOrder(order);
	}

	public void addBookToOrder(int orderId, int bookId) {
		OrderBookRelation relation = new OrderBookRelation(orderId, bookId);
		orderBookManager.addOrderBookRelation(relation);
		createRelation(relation);
	}

	private void refreshRelation() {
		for (OrderBookRelation relation : orderBookManager.getRelations())
			if (relation != null)
				createRelation(relation);
	}

	private void createRelation(OrderBookRelation relation) {
		orderManager.addOrderBookRelation(relation, bookManager.getBook(relation.getBookId()).getPrice());
		bookManager.addOrderBookRelation(relation);
	}

	public void completeOrder(int orderId) {
		OrderBookRelation[] relationList = orderManager.getOrder(orderId).getOrderBookList();
		if (bookManager.isBookOnStock(relationList)) {
			orderManager.completeOrder(orderId);
			bookManager.writeOffBook(relationList);
		}
	}

	public void cancelOrder(Order order) {
		orderManager.cancelOrder(order);
	}

	public void showOrderDetails(int orderId) {
		Order order = orderManager.getOrder(orderId);
		Printer.print(order);
		Printer.print(order.getOrderBookList());
	}

	public void showOrders(SortOrderType type) {
		Printer.print(sortOrder(type));
	}

	public void showOrders(Date dateAfter, Date dateBefore, SortOrderType type) {
		Printer.print(sortOrder(type), dateAfter, dateBefore);
	}

	private Order[] sortOrder(SortOrderType type) {
		Order[] order;
		switch (type) {
		case BY_EXECUTION_DATE:
			order = orderManager.sortOrderList(new OrderByDateComparator());
			break;
		case BY_PRICE:
			order = orderManager.sortOrderList(new OrderByPriceComparator());
			break;
		case BY_STATUS:
			order = orderManager.sortOrderList(new OrderByStatusComparator());
			break;
		default:
			order = null;
			break;
		}
		return order;
	}

	public void showCompletedOrderQuantity(Date dateBefore, Date dateAfter) {
		Printer.print(orderManager.getCompletedOrderQuantity(dateBefore, dateAfter));
	}

	public void showTotalIncome(Date dateBefore, Date dateAfter) {
		Printer.print(String.valueOf(orderManager.getTotalIncome(dateBefore, dateAfter)));
	}

	public void addRequest(Request request) {
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
