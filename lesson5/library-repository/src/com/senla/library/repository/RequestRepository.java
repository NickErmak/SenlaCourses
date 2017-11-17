package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.bean.IRequest;
import com.senla.library.entity.Request;
import com.senla.library.util.ArrayHandler;

public class RequestRepository implements IRequestRepository{
	
	private TextFileWorker textFileWorker;
	private List<IRequest> requestList;

	public RequestRepository(String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		requestList = new ArrayList<>();
		readData();
	}

	public void addRequest(IRequest request) {
		requestList.add(request);
	}

	public IRequest getRequest(int id) {
		return ArrayHandler.getElementById(id, requestList);
	}

	public void readData() {
		for (String request : textFileWorker.readFromFile())
			addRequest(new Request(request.split("  ")));
	}

	public void saveData() {
		textFileWorker.writeToFile(ArrayHandler.getStringArray(requestList));
	}

}
