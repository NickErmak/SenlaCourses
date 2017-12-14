package com.senla.library.manager;

import static com.senla.library.csv.CSVHandler.CSVFileReader.read;
import static com.senla.library.csv.CSVHandler.CSVFileWriter.write;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.bean.Status;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;
import com.senla.library.api.repository.IRequestRepository;
import com.senla.library.api.service.IRequestManager;
import com.senla.library.entity.Request;
import com.senla.library.repository.RepositoryShell;

public class RequestManager implements IRequestManager{
	
	private IRequestRepository requestRepository;
	
	public RequestManager(String filePath) {
		requestRepository = RepositoryShell.getRequestRepository();
		requestRepository.readData(filePath);
	}
	
	public void addRequest(IRequest request) {
		requestRepository.addRequest(request);
	}

	public IRequest getRequest(Integer requestId) throws NoSuchIdException {
		return requestRepository.getRequest(requestId);
	}

	public void refreshRequest(IRequest deprecatedRequest, IRequest refreshedRequest) {
		requestRepository.refreshRequest(deprecatedRequest, refreshedRequest);
	}

	public List<IRequest> getRequests() {
		return requestRepository.getRequests();
	}

	public void completeRequest(IRequest request) {
		request.setStatus(Status.COMPLETED);
		request.setDate(new Date());
	}

	public void exportCSV(String filePath) throws NonParseableException {
		List<IRequest> requests = getRequests();
		if (!requests.isEmpty()) {
			write(getRequests(), filePath);
		}
	}

	public void importCSV(String filePath) throws NonParseableException {
		Iterator<Request> iteratorCSV = read(Request.class, filePath).iterator();
		while (iteratorCSV.hasNext()) {
			IRequest requestCSV = iteratorCSV.next();
			try {
				IRequest request = getRequest(requestCSV.getId());
				if (request != null) {
					refreshRequest(request, requestCSV);
				}
			} catch (NoSuchIdException e) {
				addRequest(requestCSV);
			}
		}
	}

	public void saveData(String filePath) {
		requestRepository.saveData(filePath);
	}
}
