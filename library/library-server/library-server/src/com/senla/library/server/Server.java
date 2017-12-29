package com.senla.library.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.senla.library.api.bean.IBook;
import com.senla.library.api.bean.IOrder;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.bean.IRequest;
import com.senla.library.api.comparator.book.SortBookType;
import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.api.facade.ILibraryManager;
import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;
import com.senla.library.api.ui.menu.BookMenuType;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.api.ui.menu.RequestMenuType;
import com.senla.library.api.ui.menu.TotalMenuType;
import com.senla.library.entity.Book;
import com.senla.library.entity.Order;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.entity.Request;
import com.senla.library.facade.LibraryManager;
import com.senla.library.transmission.Response;
import com.senla.library.util.DateConverter;
import com.senla.library.util.Printer;

public class Server {
	private static Logger logger = Logger.getLogger(Server.class);
	private static Server instance;
	private ILibraryManager libraryManager;
	
	static {
		DOMConfigurator.configure("resources/log4j.xml");
	}

	private Server() {
		libraryManager = LibraryManager.getInstance();
		createServer();		
	}

	private void createServer() {
		try(ServerSocket serverSocket = new ServerSocket(8071)) {			
			Printer.print("initialized");
			while (true) {
				Socket socket = serverSocket.accept();
				Printer.print(socket.getInetAddress().getHostName() + " connected");
				Thread clientThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {								
							ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());	
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());	
							IQuery query = (IQuery) objectInputStream.readObject();							
							objectOutputStream.writeObject(sendQuery(query));
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e);
						}
					}
				});
				clientThread.start();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static Server getInstance() {
		if (instance == null) {
			instance = new Server();
		}
		return instance;
	}

	private IResponse sendQuery(IQuery query) {
		Map<String, Object> actionInfo = query.getActionInfo();
		IResponse response = new Response(actionInfo.get("message").toString());
		try {
			switch ((MainMenuType) actionInfo.get("type")) {
			case BOOK: {
				sendBookQuery(actionInfo, response);
				break;
			}
			case ORDER: {
				sendOrderQuery(actionInfo, response);
				break;
			}
			case REQUEST: {
				sendRequestQuery(actionInfo, response);
				break;
			}
			case TOTAL: {
				sendTotalQuery(actionInfo, response);
				break;
			}
			case EXIT: {
				response.completeMessage(libraryManager.exitProgram().toString());
				response.setExit(true);
				break;
			}
			}
		} catch (NoSuchIdException e) {
			response.completeMessage(e.toString());
			logger.error(e);
		} catch (NonParseableException e) {
			response.completeMessage(e.toString());
			logger.error(e);
		}
		return response;
	}

	private void sendBookQuery(Map<String, Object> actionInfo, IResponse response)
			throws NoSuchIdException, NonParseableException {
		switch ((BookMenuType) actionInfo.get("bookType")) {
		case ADD: {
			String input[] = actionInfo.get("input").toString().split("--");
			IBook book = new Book(input[0], DateConverter.stringToDate(input[1]), Double.valueOf(input[2]), input[3],
					Boolean.valueOf(input[4]));
			response.completeMessage(libraryManager.addBook(book).toString());
			break;
		}
		case SHOW_ALL: {
			SortBookType sortBookType = (SortBookType) actionInfo.get("bookSortType");			
			response.setEntities(libraryManager.showBooks(sortBookType));
			break;
		}
		case SHOW_DESCRIPTION: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.showBookDescription(id));
			break;
		}
		case SHOW_QUERY: {
			break;
		}
		case SHOW_UNSOLD: {
			response.setEntities(libraryManager.showUnsoldBooks());
			break;
		}
		case WRITE_OFF: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.writeOffBook(id).toString());
			break;
		}
		case EXPORT: {
			response.completeMessage(libraryManager.exportCSVBook().toString());
			break;
		}
		case IMPORT: {
			response.completeMessage(libraryManager.importCSVBook().toString());
			break;
		}
		}
	}

	private void sendOrderQuery(Map<String, Object> actionInfo, IResponse response)
			throws NoSuchIdException, NonParseableException {
		switch ((OrderMenuType) actionInfo.get("orderType")) {
		case ADD: {
			IOrder order = new Order(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.addOrder(order).toString());
			break;
		}
		case ADD_BOOK_TO_ORDER: {
			String id[] = actionInfo.get("input").toString().split("--");
			IOrderBookRelation relation = new OrderBookRelation(Integer.valueOf(id[0]), Integer.valueOf(id[1]));
			response.completeMessage(libraryManager.addBookToOrder(relation).toString());
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
		case SHOW_ALL: {
			SortOrderType sortOrderType = (SortOrderType) actionInfo.get("orderSortType");
			response.setEntities(libraryManager.showOrders(sortOrderType));
			break;
		}
		case SHOW_COMPLETED_QUANTITY: {
			String input[] = actionInfo.get("input").toString().split("-");
			int quantity = libraryManager.showCompletedOrderQuantity(DateConverter.stringToDate(input[0]),
					DateConverter.stringToDate(input[1]));
			response.completeMessage(String.valueOf(quantity));
			break;
		}
		case SHOW_FOR_DATE_PERIOD: {
			break;
		}
		case SHOW_DETAILS: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.showOrderDetails(id).toString());
			break;
		}
		case CLONE: {
			int id = Integer.valueOf(actionInfo.get("input").toString());
			response.completeMessage(libraryManager.cloneOrder(id).toString());
			break;
		}
		case EXPORT: {
			response.completeMessage(libraryManager.exportCSVOrder().toString());
			break;
		}
		case IMPORT: {
			response.completeMessage(libraryManager.importCSVOrder().toString());
			break;
		}
		}
	}

	private void sendRequestQuery(Map<String, Object> actionInfo, IResponse response)
			throws NoSuchIdException, NonParseableException {
		switch ((RequestMenuType) actionInfo.get("requestType")) {
		case ADD:
			int id = Integer.valueOf(actionInfo.get("input").toString());
			IRequest request = new Request(id);
			response.completeMessage(libraryManager.addRequest(request).toString());
			break;
		case EXPORT:
			response.completeMessage(libraryManager.exportCSVRequest().toString());
			break;
		case IMPORT:
			response.completeMessage(libraryManager.importCSVRequest().toString());
			break;
		}
	}

	private void sendTotalQuery(Map<String, Object> actionInfo, IResponse response) {
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
