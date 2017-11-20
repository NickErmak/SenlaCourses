package com.senla.library.ui.transmitter;

import java.util.HashMap;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.IRequest;
import com.senla.library.api.comparator.book.SortBookType;
import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.facade.ILibraryManager;
import com.senla.library.api.transmitter.ITransmitter;
import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;
import com.senla.library.api.ui.menu.BookMenuType;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.api.ui.menu.RequestMenuType;
import com.senla.library.api.ui.menu.TotalMenuType;
import com.senla.library.entity.Book;
import com.senla.library.entity.Order;
import com.senla.library.entity.Request;
import com.senla.library.facade.LibraryManager;
import com.senla.library.util.DateConverter;

public class Transmitter implements ITransmitter {

	private static ITransmitter instance;

	private ILibraryManager libraryManager;

	private Transmitter() {
		try {
			libraryManager = LibraryManager.getInstance();
		} catch (NoSuchIdException e) {
			e.printStackTrace();
		}
	}

	public static ITransmitter getInstance() {
		if (instance == null)
			instance = new Transmitter();
		return instance;
	}

	public IResponse sendQuery(IQuery query) throws NoSuchIdException {
		HashMap<String, Object> actionInfo = query.getActionInfo();
		IResponse response = new Response(actionInfo.get("message").toString());
		switch ((MainMenuType) actionInfo.get("type")) {
		case BOOK:
			sendBookQuery(actionInfo, response);
			break;
		case ORDER:
			sendOrderQuery(actionInfo, response);
			break;
		case REQUEST:
			sendRequestQuery(actionInfo, response);
			break;
		case TOTAL:
			sendTotalQuery(actionInfo, response);
			break;
		case EXIT:
			response.completeMessage(libraryManager.exitProgram().toString());
			response.setExit(true);
			break;
		}
		return response;
	}

	private void sendBookQuery(HashMap<String, Object> actionInfo, IResponse response) throws NoSuchIdException {
		switch ((BookMenuType) actionInfo.get("bookType")) {
		case ADD:
			String input[] = actionInfo.get("input").toString().split("--");
			IBook book = new Book(input[0], DateConverter.stringToDate(input[1]), Double.valueOf(input[2]), input[3]);
			response.completeMessage(libraryManager.addBook(book).toString());
			break;
		case SHOW_ALL:
			SortBookType sortBookType = (SortBookType) actionInfo.get("bookSortType");
			response.setEntities(libraryManager.showBooks(sortBookType));
			break;
		case SHOW_DESCRIPTION: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.showBookDescription(id));
			break;
		}
		case SHOW_QUERY:
			break;
		case SHOW_UNSOLD:
			response.setEntities(libraryManager.showUnsoldBooks());
			break;
		case WRITE_OFF: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.writeOffBook(id).toString());
			break;
		}
		}
	}

	private void sendOrderQuery(HashMap<String, Object> actionInfo, IResponse response) throws NoSuchIdException {
		switch ((OrderMenuType) actionInfo.get("orderType")) {
		case ADD:
			IOrder order = new Order(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.addOrder(order).toString());
			break;
		case ADD_BOOK_TO_ORDER: {
			String id[] = actionInfo.get("input").toString().split("--");
			response.completeMessage(
					libraryManager.addBookToOrder(Integer.valueOf(id[0]), Integer.valueOf(id[1])).toString());
		}
			break;
		case CANCEL: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.cancelOrder(id).toString());
			break;
		}
		case COMPLETE: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.completeOrder(id).toString());
			break;
		}
		case SHOW_ALL:
			SortOrderType sortOrderType = (SortOrderType) actionInfo.get("orderSortType");
			response.setEntities(libraryManager.showOrders(sortOrderType));
			break;
		case SHOW_COMPLETED_QUANTITY:
			String input[] = actionInfo.get("input").toString().split("-");
			int quantity = libraryManager.showCompletedOrderQuantity(DateConverter.stringToDate(input[0]),
					DateConverter.stringToDate(input[1]));
			response.completeMessage(String.valueOf(quantity));
			break;
		case SHOW_FOR_DATE_PERIOD:
			break;
		case SHOW_DETAILS: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.showOrderDetails(id).toString());
			break;
		}
		}
	}

	private void sendRequestQuery(HashMap<String, Object> actionInfo, IResponse response) throws NoSuchIdException {
		switch ((RequestMenuType) actionInfo.get("requestType")) {
		case ADD:
			int id = Integer.valueOf(actionInfo.get("input").toString());
			IRequest request = new Request(id);
			response.completeMessage(libraryManager.addRequest(request).toString());
			break;
		}
	}

	private void sendTotalQuery(HashMap<String, Object> actionInfo, IResponse response) {
		switch ((TotalMenuType) actionInfo.get("totalType")) {
		case SHOW_TOTAL_INCOME:
			String input[] = actionInfo.get("input").toString().split("-");
			Double totalIncome = libraryManager.showTotalIncome(DateConverter.stringToDate(input[0]),
					DateConverter.stringToDate(input[1]));
			response.completeMessage(totalIncome.toString());
			break;
		}
	}

}
