package com.senla.library;

import java.util.Date;

import com.senla.library.comparator.book.*;
import com.senla.library.comparator.order.*;
import com.senla.library.comparator.query.*;
import com.senla.library.entity.*;
import com.senla.library.util.Printer;

public class LibraryManager implements ILibraryManager {

	private BookManager bookManager;
	private OrderManager orderManager;
	private RequestManager requestManager;

	public LibraryManager(int maxBook, int maxOrder, int maxRequest) {
		bookManager = new BookManager(maxBook);
		orderManager = new OrderManager(maxOrder);
		requestManager = new RequestManager(maxRequest);
		refreshRelationById();
	}

	public void addBook(Book book) {
		bookManager.addBook(book);
		if (book.getRequest() != null)
			completeRequest(book.getRequest());
	}

	public void writeOffBook(int bookId) {
		bookManager.writeOffBook(bookId);
	}

	public void showBookList(SortBookType type) {
		switch (type) {
		case ALPHABETICALLY:
			Printer.print(bookManager.sortBookList(new BookByTitleComparator()));
			break;
		case BY_PRICE:
			Printer.print(bookManager.sortBookList(new BookByPriceComparator()));
			break;
		case BY_PUBLICATION_DATE:
			Printer.print(bookManager.sortBookList(new BookByDateComparator()));
			break;
		case BY_STOCK:
			Printer.print(bookManager.sortBookList(new BookByOnStockComparator()));
			break;
		}
	}

	public void showBookQuery(SortQueryType type) {
		switch (type) {
		case ALPHABETICALLY:
			Printer.printQuery(bookManager.sortBookList(new BookQueryByTitle()));
			break;
		case QUANTITY:
			Printer.printQuery(bookManager.sortBookList(new BookQueryByQuantity()));
			break;
		}
	}

	public void showBadSellingBook() {
		bookManager.showBadSellingBook();
	}

	public void showBookDescription(int bookId) {
		bookManager.showBookDescription(bookId);
	}

	public void addOrder(Order order) {
		orderManager.addOrder(order);
	}

	public void addBookToOrder(int orderId, int bookId) {
		OrderBookRelation relation = new OrderBookRelation(orderManager.getOrder(orderId), bookManager.getBook(bookId));
		orderManager.addOrderBookRelation(relation);
		bookManager.addOrderBookRelation(relation);
		orderManager.calculateTotalAmount(relation);
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
		orderManager.showOrderDetails(orderId);
	}

	public void showOrderList(SortOrderType type) {
		Printer.print(sortOrder(type));
	}

	public void showOrderList(Date dateBefore, Date dateAfter, SortOrderType type) {
		Printer.print(sortOrder(type), dateBefore, dateAfter);
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

	public double getTotalIncome(Date dateBefore, Date dateAfter) {
		return orderManager.getTotalIncome(dateBefore, dateAfter);
	}

	public void showCompletedOrderQuantity(Date dateBefore, Date dateAfter) {
		Printer.print(orderManager.getCompletedOrderQuantity(dateBefore, dateAfter));
	}

	public void addRequest(Request request) {
		requestManager.addRequest(request);
		bookManager.setRequest(request);
	}

	public void completeRequest(Request request) {
		requestManager.completeRequest(request);
	}

	private void refreshRelationById() {
		Book[] bookList = bookManager.getBookList();
		for (int i = 0; i < bookList.length; i++) {
			if (bookList[i] != null) {
				if (bookList[i].getRequestId() == null)
					bookList[i].setRequest(null);
				else
					bookList[i].setRequest(requestManager.getRequest(Integer.valueOf(bookList[i].getRequestId())));
				int relationQuantity = bookList[i].getOrderBookRelationIdList().length;
				for (int ii = 0; ii < relationQuantity; ii++) {
					OrderBookRelation relation = null;
					String id = bookList[i].getOrderBookRelationIdList()[ii];
					if (!id.equals("null"))
						relation = new OrderBookRelation(orderManager.getOrder(Integer.valueOf(id)), bookList[i]);
					bookList[i].addOrderBookRelation(relation);
				}
			}
		}
		Order[] orderList = orderManager.getOrderList();
		for (int i = 0; i < orderList.length; i++) {
			if (orderList[i] != null) {
				int relationQuantity = orderList[i].getOrderBookRelationIdList().length;
				for (int ii = 0; ii < relationQuantity; ii++) {
					OrderBookRelation relation = null;
					String id = orderList[i].getOrderBookRelationIdList()[ii];
					if (!id.equals("null"))
						new OrderBookRelation(orderList[i],
								bookManager.getBook(Integer.valueOf(orderList[i].getOrderBookRelationIdList()[ii])));
					orderList[i].addOrderBookRelation(relation);
				}
			}
		}
	}

	public void exitProgram() {
		bookManager.save();
		orderManager.save();
		requestManager.save();
	}

}
