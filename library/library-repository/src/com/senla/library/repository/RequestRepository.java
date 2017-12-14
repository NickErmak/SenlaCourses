package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.annotation.di.Singleton;
import com.senla.library.api.bean.IRequest;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IRequestRepository;
import com.senla.library.util.CollectionHandler;
import com.senla.library.util.FileWorker;

@Singleton
public class RequestRepository implements IRequestRepository {
	private static IRequestRepository requestRepository;
	private List<IRequest> requests;

	private RequestRepository() {
		requests = new ArrayList<>();
	}

	public static IRequestRepository getInstance() {
		if (requestRepository == null) {
			requestRepository = new RequestRepository();
		}
		return requestRepository;
	}

	@Override
	public void addRequest(IRequest request) {
		requests.add(request);
	}

	@Override
	public IRequest getRequest(int id) throws NoSuchIdException {
		return CollectionHandler.getElementById(id, requests);
	}

	@Override
	public void refreshRequest(IRequest deprecatedRequest, IRequest refreshedRequest) {
		requests.remove(deprecatedRequest);
		requests.add(refreshedRequest);
	}

	@Override
	public List<IRequest> getRequests() {
		return requests;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readData(String filePath) {
		Object fileData = FileWorker.read(filePath);
		if (fileData != null) {
			requests = (List<IRequest>) fileData;
		}
	}

	@Override
	public void saveData(String filePath) {
		FileWorker.save(requests, filePath);
	}
}
