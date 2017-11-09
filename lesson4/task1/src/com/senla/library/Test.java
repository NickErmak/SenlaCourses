package com.senla.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.senla.library.api.ILibraryManager;
import com.senla.library.comparator.book.SortBookQueryType;
import com.senla.library.comparator.book.SortBookType;
import com.senla.library.comparator.order.SortOrderType;
import com.senla.library.entity.Book;
import com.senla.library.entity.Order;
import com.senla.library.entity.Request;
import com.senla.library.manager.LibraryManager;

public class Test {

	public static void main(String args[]) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = format.parse("10/12/1945");		
		Book book1 = new Book("1984", date1, 10, "description1");		
		ILibraryManager libManager = new LibraryManager(100, 100, 100, args);
 		libManager.addBook(book1);		
		libManager.showBooks(SortBookType.BY_STOCK);
		Order order1 = new Order("Order1");		
		libManager.addOrder(order1);		
		libManager.showOrders(SortOrderType.BY_STATUS);
		libManager.showBookQuery(SortBookQueryType.BY_QUANTITY);
		libManager.showOrderDetails(order1.getId());
		libManager.showOrders(format.parse("10/12/2000"), format.parse("10/12/2018"), SortOrderType.BY_PRICE);		
		libManager.showTotalIncome(format.parse("10/12/2000"), format.parse("10/12/2018"));
		libManager.showCompletedOrderQuantity(format.parse("10/12/2000"), format.parse("10/12/2018"));
		libManager.showUnsoldBooks();; 
		libManager.showOrderDetails(order1.getId());
		libManager.showBookDescription(book1.getId());
		libManager.writeOffBook(book1.getId());
		libManager.addBookToOrder(order1.getId(), book1.getId());			
		libManager.completeOrder(order1.getId());
		libManager.showOrderDetails(order1.getId());
		Request request1 = new Request(book1.getId());
		libManager.addRequest(request1);
		libManager.addBook(book1);
		libManager.completeOrder(order1.getId());
		libManager.showOrderDetails(order1.getId());
		libManager.exitProgram();
	}
}
