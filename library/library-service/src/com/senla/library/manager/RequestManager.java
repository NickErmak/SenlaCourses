package com.senla.library.manager;

import java.util.Date;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.bean.Status;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.repository.RequestRepository;

public class RequestManager {

	private final RequestRepository requestRepository;

	public RequestManager() {
		requestRepository = RequestRepository.getInstance();
	}

	public void addRequest(IRequest request) {
		requestRepository.addRequest(request);
	}

	public IRequest getRequest(Integer requestId) throws NoSuchIdException {
		return requestRepository.getRequest(requestId);
	}

	public void completeRequest(IRequest request) {
		request.setStatus(Status.COMPLETED);
		request.setDate(new Date());
	}

	public void save() {		
		requestRepository.saveData();
	}

}
