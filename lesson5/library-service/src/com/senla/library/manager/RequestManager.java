package com.senla.library.manager;

import java.util.Date;

import com.senla.library.bean.IRequest;
import com.senla.library.enums.Status;
import com.senla.library.repository.RequestRepository;

public class RequestManager {

	private final RequestRepository requestRepository;

	public RequestManager(String filePath) {
		requestRepository = new RequestRepository(filePath);
	}

	public void addRequest(IRequest request) {
		requestRepository.addRequest(request);
	}

	public IRequest getRequest(int requestId) {
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
