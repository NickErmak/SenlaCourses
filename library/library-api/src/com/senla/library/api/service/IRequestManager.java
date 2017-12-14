package com.senla.library.api.service;

import com.senla.library.api.bean.IRequest;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.exception.NonParseableException;

public interface IRequestManager {
	IRequest getRequest(Integer requestId) throws NoSuchIdException;
	void completeRequest(IRequest request);
	void addRequest(IRequest request);
	void exportCSV(String string) throws NonParseableException;
	void importCSV(String string) throws NonParseableException;
	void saveData(String string);
}
