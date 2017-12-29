package com.senla.library.ui.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;

public class ClientSocket {
	private static Logger logger = Logger.getLogger(ClientSocket.class);

	public static IResponse sendQuery(IQuery query) {
		IResponse response = null;
		try (Socket socket = new Socket(InetAddress.getLocalHost(), 8071)) {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream.writeObject(query);
			response = (IResponse) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e);
		}
		return response;
	}
}
