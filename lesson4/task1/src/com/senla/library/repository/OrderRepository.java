package com.senla.library.repository;

import java.text.ParseException;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.Order;
import com.senla.library.entity.Status;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.DateConverter;

public class OrderRepository {

	public static final int ID_LAST_DIGIT = 3;
	public static final int MAX_BOOK = 10;
	private final String FILE_PATH = "d:/order_file.txt";
	private TextFileWorker textFileWorker;
	private Order[] orderList;

	public OrderRepository(int orderMax) {
		textFileWorker = new TextFileWorker(FILE_PATH);
		readOrderFile();
	}

	public void addOrder(Order order) {
		orderList[ArrayHandler.getFreeCellIndex(orderList)] = order;
	}

	public Order getOrder(int orderId) {
		int index = ArrayHandler.getElementIndex(orderId, orderList);
		if (index != -1)
			return orderList[index];
		else
			return null;
	}

	public Order[] getOrderList() {
		return orderList;
	}

	public void save() {
		textFileWorker.writeToFile(getOrderStringArray());
	}

	public void readOrderFile() {
		String[] orderArray = textFileWorker.readFromFile();
		orderList = new Order[orderArray.length + 5];
		for (int i = 0; i < orderArray.length; i++) {
			String[] orderToString = orderArray[i].split("%%");
			Order order;
			try {
				order = new Order(orderToString[1]);
				order.setId(new Integer(orderToString[0]));
				order.setDate(DateConverter.stringToDate(orderToString[2]));
				order.setTotalAmount(Double.valueOf(orderToString[3]));
				order.setStatus(Status.getStatus(orderToString[4]));
				order.setOrderBookRelationIdList(orderToString[5].split("id"));
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
				order = null;
			}
			orderList[i] = order;
		}
	}

	private String[] getOrderStringArray() {
		String[] orderArray = new String[ArrayHandler.getElementQuantity(orderList)];
		for (int i = 0; i < orderArray.length; i++) {
			StringBuilder orderToString = new StringBuilder();
			orderToString.append(orderList[i].getId()).append("%%");
			orderToString.append(orderList[i].getName()).append("%%");
			orderToString.append(DateConverter.dateToString(orderList[i].getDate())).append("%%");
			orderToString.append(orderList[i].getTotalAmount()).append("%%");
			orderToString.append(orderList[i].getStatus()).append("%%");
			orderToString.append(orderList[i].getOrderBookRelationId()).append("%%");
			orderArray[i] = orderToString.toString();
		}
		return orderArray;
	}

}
