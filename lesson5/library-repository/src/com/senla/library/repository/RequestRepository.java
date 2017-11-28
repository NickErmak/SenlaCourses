package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IRequestRepository;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.FileWorker;

public class RequestRepository implements IRequestRepository {

	private static RequestRepository instance;
	private static final String FILE_PATH = "data/request.ser";
	private List<IRequest> requests;

	public static RequestRepository getInstance() {
		if (instance == null)
			instance = new RequestRepository();
		return instance;
	}

	private RequestRepository() {
		requests = new ArrayList<>();
		readData();
	}

	public void addRequest(IRequest request) {
		requests.add(request);
	}

	public IRequest getRequest(Integer id) throws NoSuchIdException {
		return ArrayHandler.getElementById(id, requests);
	}

	@SuppressWarnings("unchecked")
	public void readData() {
		Object fileData = FileWorker.read(FILE_PATH);
		if (fileData != null)
			requests = (List<IRequest>) fileData;
	}

	public void saveData() {
		FileWorker.save(requests, "data/request.ser");
	}

}
