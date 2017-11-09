package com.senla.library.repository;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.Request;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.FileWorker;

public class RequestRepository {

	public static final int ID_LAST_DIGIT = 2;
	private Request[] requestList;
	private TextFileWorker textFileWorker;

	public RequestRepository(int maxRequest, String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		requestList = new Request[maxRequest];
		readData();
	}

	public void addRequest(Request request) {
		requestList[ArrayHandler.getFreeCellIndex(requestList)] = request;
	}

	public Request getRequest(int RequestId) {
		return (Request) ArrayHandler.getElementById(RequestId, requestList);
	}

	public void readData() {
		FileWorker.readData(textFileWorker, requestList, new Request());
	}

	public void saveData() {
		FileWorker.saveData(textFileWorker, requestList);
	}

}
