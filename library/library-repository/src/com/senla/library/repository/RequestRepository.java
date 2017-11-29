package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.api.bean.IRequest;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IRequestRepository;
import com.senla.library.entity.Request;
import com.senla.library.util.ArrayHandler;

public class RequestRepository implements IRequestRepository{
	
	private static RequestRepository instance;
	private static final String FILE_PATH = "data/request.txt";
	private TextFileWorker textFileWorker;
	private List<IRequest> requestList;

	private RequestRepository() {
		textFileWorker = new TextFileWorker(FILE_PATH);
		requestList = new ArrayList<>();
		readData();
	}

	public static RequestRepository getInstance() throws NoSuchIdException {
		if (instance == null)
			instance = new RequestRepository();
		return instance;
	}
	
	public void addRequest(IRequest request) {
		requestList.add(request);
	}

	public IRequest getRequest(int id) throws NoSuchIdException {
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
