package com.senla.library.manager;

import static com.senla.library.util.CSVWorker.loadCSV;
import static com.senla.library.util.CSVWorker.saveCSV;
import static com.senla.library.util.CollectionHandler.parseToStringWithHeader;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.bean.Status;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.entity.Request;
import com.senla.library.repository.RequestRepository;

public class RequestManager {
	private final static String[] CSV_HEAD = { "Id", "book Id", "date", "status" };
	private final RequestRepository requestRepository;

	public RequestManager(String filePath) {
		requestRepository = RequestRepository.getInstance();
		requestRepository.readData(filePath);
	}

	public void addRequest(IRequest request) {
		requestRepository.addRequest(request);
	}

	public IRequest getRequest(Integer requestId) throws NoSuchIdException {
		return requestRepository.getRequest(requestId);
	}

	public List<IRequest> getRequests() {
		return requestRepository.getRequests();
	}

	public void completeRequest(IRequest request) {
		request.setStatus(Status.COMPLETED);
		request.setDate(new Date());
	}

	public void exportCSV(String filePath) {
		saveCSV(filePath, parseToStringWithHeader(getRequests(), CSV_HEAD));
	}

	public void importCSV(String filePath) {
		ListIterator<String[]> listIterator = loadCSV(filePath).listIterator(1);
		while (listIterator.hasNext()) {
			IRequest requestCSV = new Request(listIterator.next());
			try {
				IRequest request = getRequest(requestCSV.getId());
				if (request != null) {
					request = requestCSV;
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
