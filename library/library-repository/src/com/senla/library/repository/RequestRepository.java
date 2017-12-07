package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.util.CollectionHandler;
import com.senla.library.util.FileWorker;

public class RequestRepository {
	private static RequestRepository instance;
	private List<IRequest> requests;

	private RequestRepository() {
		requests = new ArrayList<>();
	}

	public static RequestRepository getInstance() {
		if (instance == null) {
			instance = new RequestRepository();
		}
		return instance;
	}

	public void addRequest(IRequest request) {
		requests.add(request);
	}

	public IRequest getRequest(Integer id) throws NoSuchIdException {
		return CollectionHandler.getElementById(id, requests);
	}

	public List<IRequest> getRequests() {
		return requests;
	}

	@SuppressWarnings("unchecked")
	public void readData(String filePath) {
		Object fileData = FileWorker.read(filePath);
		if (fileData != null) {
			requests = (List<IRequest>) fileData;
		}
	}

	public void saveData(String filePath) {
		FileWorker.save(requests, filePath);
	}
}
