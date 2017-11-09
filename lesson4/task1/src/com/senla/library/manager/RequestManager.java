package com.senla.library.manager;

import java.util.Date;

import com.senla.library.entity.Request;
import com.senla.library.entity.Status;
import com.senla.library.repository.RequestRepository;

public class RequestManager {

	private final RequestRepository requestRep;

	public RequestManager(int maxRequest, String filePath) {
		requestRep = new RequestRepository(maxRequest, filePath);
	}

	public void addRequest(Request request) {
		requestRep.addRequest(request);
	}

	public Request getRequest(int requestId) {
		return requestRep.getRequest(requestId);
	}

	public void completeRequest(Request request) {
		request.setStatus(Status.COMPLETED);
		request.setDate(new Date());
	}

	public void save() {
		requestRep.saveData();
	}

}
