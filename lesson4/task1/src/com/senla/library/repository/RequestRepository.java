package com.senla.library.repository;

import java.text.ParseException;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.Request;
import com.senla.library.entity.Status;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.DateConverter;

public class RequestRepository {

	public static final int ID_LAST_DIGIT = 2;
	private final String FILE_PATH = "d:/request_file.txt";
	private Request[] requestList;
	private TextFileWorker textFileWorker;

	public RequestRepository(int maxRequest) {
		textFileWorker = new TextFileWorker(FILE_PATH);
		readRequestFile();
	}

	public void addRequest(Request request) {
		requestList[ArrayHandler.getFreeCellIndex(requestList)] = request;
	}

	public Request getRequest(int RequestId) {
		return requestList[ArrayHandler.getElementIndex(RequestId, requestList)];
	}

	public void save() {
		textFileWorker.writeToFile(getRequestStringArray());
	}

	public void readRequestFile() {
		String[] requestArray = textFileWorker.readFromFile();
		requestList = new Request[requestArray.length + 5];
		for (int i = 0; i < requestArray.length; i++) {
			if (requestArray[i] != null) {
				String[] requestToString = requestArray[i].split("%%");
				Request request;
				try {
					request = new Request(Integer.valueOf(requestToString[2]));
					request.setId(new Integer(requestToString[0]));
					request.setDate(DateConverter.stringToDate(requestToString[1]));
					request.setStatus(Status.getStatus(requestToString[3]));
				} catch (NumberFormatException | ParseException e) {
					e.printStackTrace();
					request = null;
				}
				requestList[i] = request;
			}
		}
	}

	private String[] getRequestStringArray() {
		String[] requestArray = new String[ArrayHandler.getElementQuantity(requestList)];
		for (int i = 0; i < requestArray.length; i++) {
			StringBuilder requestToString = new StringBuilder();
			requestToString.append(requestList[i].getId()).append("%%");
			requestToString.append(DateConverter.dateToString(requestList[i].getDate())).append("%%");
			requestToString.append(requestList[i].getBookId()).append("%%");
			requestToString.append(requestList[i].getStatus()).append("%%");
			requestArray[i] = requestToString.toString();
		}
		return requestArray;
	}

}
