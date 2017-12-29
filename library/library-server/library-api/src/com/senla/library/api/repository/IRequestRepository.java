package com.senla.library.api.repository;

import java.util.List;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.exception.NoSuchIdException;

public  interface IRequestRepository {
	
	void addRequest(IRequest request);
	IRequest getRequest(int id) throws NoSuchIdException;
	void refreshRequest(IRequest deprecatedRequest, IRequest refreshedRequest);
	List<IRequest> getRequests();
	void readData(String filePath);
	void saveData(String filePath);
}
