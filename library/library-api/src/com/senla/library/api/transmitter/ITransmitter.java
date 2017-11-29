package com.senla.library.api.transmitter;

import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;

public interface ITransmitter {
	
	public IResponse sendQuery(IQuery query) throws NoSuchIdException, CloneNotSupportedException;
}
