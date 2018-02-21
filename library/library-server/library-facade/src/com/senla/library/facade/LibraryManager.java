package com.senla.library.facade;

import static com.senla.library.ioc.DIHandler.injectDependencies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.comparator.order.OrderByDateComparator;
import com.senla.library.api.comparator.order.OrderByPriceComparator;
import com.senla.library.api.comparator.order.OrderByStatusComparator;
import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.config.PropertyUnit;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.api.ui.ConsoleMessage;
import com.senla.library.config.reader.PropertyReader;
import com.senla.library.manager.BookManager;
import com.senla.library.manager.OrderManager;

public class LibraryManager implements Serializable {
	private static final long serialVersionUID = 1495183262972619742L;
	private static volatile LibraryManager instance;
	private BookManager bookManager;
	private OrderManager orderManager;
	private Map<PropertyUnit, String> properties;

	private LibraryManager() {
		injectDependencies();
		reloadProperties();
		bookManager = new BookManager(properties.get(PropertyUnit.DATA_BOOK));
		orderManager = new OrderManager(properties.get(PropertyUnit.DATA_ORDER));			
	}	

	public static LibraryManager getInstance() {
		if (instance == null) {
			instance = new LibraryManager();
		}
		return instance;
	}

	private void reloadProperties() {
		properties = PropertyReader.getInstance().loadTotal();
	}


	public void addBook(IBook book) {
		bookManager.addBook(book);		
	}

	
	public synchronized void writeOffBook(IBook book) throws NoSuchIdException {
		bookManager.writeOffBook(book);		
	}

	public List<IBook> getBooks(SortingCriteria sortingCriteria) {
		return bookManager.getBooks(sortingCriteria);
	}

	
	public List<IBook> getUnsoldBooks(SortingCriteria sortingCriteria) {
		reloadProperties();
		int unsoldMonthAmount = Integer.valueOf(properties.get(PropertyUnit.UNSOLD_MONTH));
		return bookManager.getUnsoldBooks(sortingCriteria, unsoldMonthAmount);
	}

	public String getBookDescription(String bookId) {
		return null;
	}
	
	public ConsoleMessage exportCSVBook() throws NonParseableException {
		reloadProperties();
		bookManager.exportCSV(properties.get(PropertyUnit.CSV_FOLDER));
		return ConsoleMessage.SUCCESS;
	}
	
	public ConsoleMessage importCSVBook() throws NonParseableException {
		reloadProperties();
		bookManager.importCSV(properties.get(PropertyUnit.CSV_FOLDER));
		return ConsoleMessage.SUCCESS;
	}

	public void addOrder(IOrder order) {
		orderManager.addOrder(order);		
	}

	public ConsoleMessage addBookToOrder(IBook book) {
		orderManager.addBookRelation(IBook book);
	}

	public synchronized ConsoleMessage completeOrder(IOrder order) throws NoSuchIdException {
		orderManager.completeOrder(order);
		bookManager.writeOffBook(order);
		return ConsoleMessage.SUCCESS;	
	}

	@Override
	public synchronized ConsoleMessage cancelOrder(IOrder order) throws NoSuchIdException {
		orderManager.cancelOrder(order);
		return ConsoleMessage.SUCCESS;
	}

	
	public List<IOrder> getOrders(SortingCriteria sortingCriteria) {		
			return orderManager.getOrders();		
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

	
	public int getCompletedOrderCount(Date dateFrom, Date dateTo) {
		return orderManager.getCompletedOrderCount(dateFrom, dateTo);
	}

	@Override
	public ConsoleMessage exportCSVOrder() throws NonParseableException {
		reloadProperties();
		orderManager.exportCSV(properties.get(PropertyUnit.CSV_FOLDER));
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public ConsoleMessage importCSVOrder() throws NonParseableException {
		reloadProperties();
		orderManager.importCSV(properties.get(PropertyUnit.CSV_FOLDER));
		return ConsoleMessage.SUCCESS;
	}

	@Override
	public double showTotalIncome(Date dateBefore, Date dateAfter) {
		return orderManager.getTotalIncome(dateBefore, dateAfter);
	}

	@Override
	public ConsoleMessage exitProgram() {
		bookManager.close();
	}
}
