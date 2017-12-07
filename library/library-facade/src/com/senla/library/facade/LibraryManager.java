package com.senla.library.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.senla.library.api.config.PropertyType;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.facade.ILibraryManager;
import com.senla.library.api.ui.ConsoleMessage;
import com.senla.library.config.reader.PropertyReader;
import com.senla.library.manager.BookManager;
import com.senla.library.manager.OrderManager;
import com.senla.library.manager.RequestManager;

public class LibraryManager implements ILibraryManager {
	private static ILibraryManager instance;
	private BookManager bookManager;
	private OrderManager orderManager;
	private RequestManager requestManager;
	private Map<PropertyType, String> properties;

	private LibraryManager() {
		reloadProperties();
		bookManager = new BookManager(properties.get(PropertyType.DATA_BOOK));
		orderManager = new OrderManager(properties.get(PropertyType.DATA_ORDER));
		requestManager = new RequestManager(properties.get(PropertyType.DATA_REQUEST));
	}

	public static ILibraryManager getInstance() {
		if (instance == null) {
			instance = new LibraryManager();
		}
		return instance;
	}

	private void reloadProperties() {
		properties = PropertyReader.getInstance().load();
	}

	@Override
	public ConsoleMessage addBook(IBook book) throws NoSuchIdException {
		reloadProperties();
		boolean autoCompleteRequest = Boolean.valueOf(properties.get(PropertyType.AUTO_COMPLETE_REQUEST));
		bookManager.addBook(book);
		if (book.getRequestId() != null) {
			IRequest request = requestManager.getRequest(book.getRequestId());
			if (autoCompleteRequest) {
				requestManager.completeRequest(request);
			}
		}
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage writeOffBook(int bookId) throws NoSuchIdException {
		bookManager.writeOffBook(bookId);
		return ConsoleMessage.SUCCESS;
	}

	@Override
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

	@Override
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

	@Override
	public List<IBook> showUnsoldBooks() throws NoSuchIdException {
		reloadProperties();
		int unsoldMonth = Integer.valueOf(properties.get(PropertyType.UNSOLD_MONTH));
		List<IBook> unsoldBooks = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, unsoldMonth);
		for (IBook book : bookManager.getBooks()) {
			Integer requestId = book.getRequestId();
			if (requestId != null && requestManager.getRequest(requestId).getDate().before(calendar.getTime())) {
				unsoldBooks.add(book);
			}
		}
		return unsoldBooks;
	}

	@Override
	public String showBookDescription(int bookId) throws NoSuchIdException {
		return bookManager.getBook(bookId).getDescription();
	}

	@Override
	public ConsoleMessage exportCSVBook() {
		reloadProperties();
		bookManager.exportCSV(properties.get(PropertyType.CSV_BOOK));
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage importCSVBook() {
		reloadProperties();
		bookManager.importCSV(properties.get(PropertyType.CSV_BOOK));
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage addOrder(IOrder order) {
		orderManager.addOrder(order);
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage addBookToOrder(IOrderBookRelation relation) throws NoSuchIdException {
		orderManager.addOrderBookRelation(relation, bookManager.getBook(relation.getBookId()).getPrice());
		bookManager.addOrderBookRelation(relation);
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage completeOrder(int orderId) throws NoSuchIdException {
		List<IOrderBookRelation> relations = orderManager.getOrder(orderId).getOrderBookList();
		if (bookManager.isBookOnStock(relations)) {
			orderManager.completeOrder(orderId);
			bookManager.writeOffBook(relations);
			return ConsoleMessage.SUCCESS;
		} else {
			return ConsoleMessage.ERROR_OUT_OF_STOCK;
		}
	}

	@Override
	public ConsoleMessage cancelOrder(int id) throws NoSuchIdException {
		orderManager.cancelOrder(id);
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public String showOrderDetails(int orderId) throws NoSuchIdException {
		IOrder order = orderManager.getOrder(orderId);
		return order.toString() + order.getOrderBookList();
	}

	@Override
	public List<IOrder> showOrders(SortOrderType type) {
		return sortOrder(type);
	}

	@Override
	public List<IOrder> showOrders(Date dateAfter, Date dateBefore, SortOrderType type) {
		List<IOrder> orders = new ArrayList<>();
		for (IOrder order : sortOrder(type)) {
			if (order.getStatus() == Status.COMPLETED) {
				if (order.getDate().before(dateBefore) && order.getDate().after(dateAfter)) {
					orders.add(order);
				}
			}
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

	@Override
	public ConsoleMessage cloneOrder(int id) throws NoSuchIdException {
		return orderManager.cloneOrder(id);
	}

	@Override
	public int showCompletedOrderQuantity(Date dateBefore, Date dateAfter) {
		return orderManager.getCompletedOrderQuantity(dateBefore, dateAfter);
	}

	@Override
	public ConsoleMessage exportCSVOrder() {
		reloadProperties();
		orderManager.exportCSV(properties.get(PropertyType.CSV_ORDER));
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage importCSVOrder() {
		reloadProperties();
		orderManager.importCSV(properties.get(PropertyType.CSV_ORDER));
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public double showTotalIncome(Date dateBefore, Date dateAfter) {
		return orderManager.getTotalIncome(dateBefore, dateAfter);
	}

	@Override
	public ConsoleMessage addRequest(IRequest request) throws NoSuchIdException {
		requestManager.addRequest(request);
		bookManager.setRequest(request);
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage exportCSVRequest() {
		reloadProperties();
		requestManager.exportCSV(properties.get(PropertyType.CSV_REQUEST));
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage importCSVRequest() {
		reloadProperties();
		requestManager.importCSV(properties.get(PropertyType.CSV_REQUEST));
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage exitProgram() {
		reloadProperties();
		bookManager.saveData(properties.get(PropertyType.DATA_BOOK));
		orderManager.saveData(properties.get(PropertyType.DATA_ORDER));
		requestManager.saveData(properties.get(PropertyType.DATA_REQUEST));
		return ConsoleMessage.SUCCESS;
	}
}
