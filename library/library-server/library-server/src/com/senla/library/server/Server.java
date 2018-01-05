package com.senla.library.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.senla.library.api.facade.ILibraryManager;
import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;
import com.senla.library.facade.LibraryManager;
import com.senla.library.transmission.Response;
import com.senla.library.util.Printer;

public class Server {
	private static Logger logger = Logger.getLogger(Server.class);
	private static Server instance;
	private ILibraryManager libraryManager;

	static {
		DOMConfigurator.configure("resources/log4j.xml");
	}

	private Server() {
		libraryManager = LibraryManager.getInstance();
		createServer();
	}

	private void createServer() {
		try (ServerSocket serverSocket = new ServerSocket(8071)) {
			Printer.print("initialized");
			while (true) {
				Socket socket = serverSocket.accept();
				Printer.print(socket.getInetAddress().getHostName() + " connected");
				Thread clientThread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
							ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
							IQuery query = (IQuery) objectInputStream.readObject();
							objectOutputStream.writeObject(sendQuery(query));
						} catch (IOException | ClassNotFoundException e) {
							logger.error(e);
						}
					}
				});
				clientThread.start();
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public static Server getInstance() {
		if (instance == null) {
			instance = new Server();
		}
		return instance;
	}

	private IResponse sendQuery(IQuery query) {
		Map<String, Object> actionInfo = query.getActionInfo();
		String methodName = actionInfo.get("method").toString();
		Object parameter = actionInfo.get("parameter");
		IResponse response = null;

		try {
			Method method = LibraryManager.class.getDeclaredMethod(methodName, parameter.getClass());
			response = new Response(actionInfo.get("message").toString(), method.invoke(libraryManager, parameter));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			logger.error(e1);
		}
		return response;
	}
}
