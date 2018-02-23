package com.senla.library.facade;

import static com.senla.library.ioc.DIHandler.injectDependencies;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.config.PropertyUnit;
import com.senla.library.api.dao.SortingCriteria;
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

	public synchronized void addBookToStorage(IBook book) throws Exception {
		bookManager.addBookToStorage(book);		
	}
	
	public void charterBook(IBook book) throws Exception {
		bookManager.charterBook(book);		
	}
	
	public synchronized void writeOffBook(IBook book) throws Exception {
		bookManager.writeOffBook(book);		
	}

	public List<IBook> getBooks(SortingCriteria sortingCriteria) throws Exception {
		return bookManager.getBooks(sortingCriteria);
	}
	
	public List<IBook> getUnsoldBooks(SortingCriteria sortingCriteria) throws Exception {
		reloadProperties();
		int unsoldMonthAmount = Integer.valueOf(properties.get(PropertyUnit.UNSOLD_MONTH));
		return bookManager.getUnsoldBooks(sortingCriteria, unsoldMonthAmount);
	}	
	
	public void exportCSVBook() throws Exception {
		reloadProperties();
		bookManager.exportCSV(properties.get(PropertyUnit.CSV_FOLDER));
	}	
	
	public void importCSVBook() throws Exception {
		reloadProperties();
		bookManager.importCSV(properties.get(PropertyUnit.CSV_FOLDER));		
	}
	
	public void addOrder(IOrder order) throws Exception {
		orderManager.addOrder(order);		
	}

	public synchronized void completeOrder(IOrder order) throws Exception {
		orderManager.completeOrder(order);		
	}
	
	public synchronized void cancelOrder(IOrder order) throws Exception {
		orderManager.cancelOrder(order);		
	}
	
	public List<IOrder> getOrders(SortingCriteria sortingCriteria) throws Exception {		
		return orderManager.getOrders(sortingCriteria);		
	}
	
	public List<IOrder> getCompletedOrders(SortingCriteria sortingCriteria, Date dateFrom, Date dateTo) throws Exception {		
		return orderManager.getCompletedOrders(sortingCriteria, dateFrom, dateTo);		
	}
	
	public double getTotalIncome(Date dateFrom, Date dateTo) throws Exception {
		return orderManager.getTotalIncome(dateFrom, dateTo);
	}
	
	public int getCompletedOrderCount(Date dateFrom, Date dateTo) throws Exception {
		return orderManager.getCompletedOrderCount(dateFrom, dateTo);
	}

	public void exportCSVOrder() throws Exception {
		reloadProperties();
		orderManager.exportCSV(properties.get(PropertyUnit.CSV_FOLDER));		
	}
	
	
	public IOrder cloneOrder(int id) throws CloneNotSupportedException, Exception {
		return orderManager.cloneOrder(id);
	}	
	
	public void importCSVOrder() throws Exception {
		reloadProperties();
		orderManager.importCSV(properties.get(PropertyUnit.CSV_FOLDER));		
	}	
	
	public void exitProgram() throws Exception {
		bookManager.exit();
		orderManager.exit();
	}
}
