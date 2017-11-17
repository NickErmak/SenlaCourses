package com.senla.library.ui.transmission;

import java.util.HashMap;

import com.senla.library.facade.ILibraryManager;
import com.senla.library.facade.LibraryManager;
import com.senla.library.ui.IQuery;
import com.senla.library.ui.IResponse;
import com.senla.library.ui.menu.BookMenuType;
import com.senla.library.ui.menu.MainMenuType;

public class Transmitter {

	private static Transmitter instance;

	private ILibraryManager libraryManager;

	private Transmitter() {
		libraryManager = new LibraryManager(new String[0]);
	}

	public static Transmitter getInstance() {
		if (instance == null)
			instance = new Transmitter();
		return instance;
	}

	public IResponse sendQuery(IQuery query) {
		HashMap<String, Object> actionInfo = query.getActionInfo();
		switch ((MainMenuType) actionInfo.get("Type")) {
		case Book:
			return sendBookQuery(actionInfo);
		case Order:
			return sendOrderQuery(actionInfo);
		case Request:
			return sendRequestQuery(actionInfo);
		case Total:
			return sendTotalQuery(actionInfo);
		case Exit:
			libraryManager.exitProgram();	
			return new Response(ResponseMessage.EXIT);
		default:
			return null;
		}		
	}

	private IResponse sendBookQuery(HashMap<String, Object> actionInfo) {
		switch ((BookMenuType) actionInfo.get("TypeBook")) {
		case Add:			
			break;
		case ShowAll:
			break;
		case ShowDescription:
			break;
		case ShowQuery:
			break;
		case ShowUnsold:
			break;
		case WriteOff:
			break;
		default:
			break;}
		return null;
		
	}

	private Response sendOrderQuery(HashMap<String, Object> actionInfo) {
		return null;
	}

	private Response sendRequestQuery(HashMap<String, Object> actionInfo) {
		return null;
	}

	private Response sendTotalQuery(HashMap<String, Object> actionInfo) {
		return null;
	}

}
