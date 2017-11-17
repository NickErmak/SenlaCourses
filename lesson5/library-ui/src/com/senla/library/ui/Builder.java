package com.senla.library.ui;

import com.senla.library.ui.IQuery;
import com.senla.library.ui.menu.Menu;
import com.senla.library.ui.transmission.Transmitter;
import com.senla.library.util.Printer;


public class Builder {

	private Menu rootMenu;
	private Transmitter transmitter;

	public Builder(Menu rootMenu) {
		transmitter = Transmitter.getInstance();
		this.rootMenu = rootMenu;		
	}
	
	public boolean buildMenu(IQuery query) {
		IResponse response = transmitter.sendQuery(query);
		Printer.print(response);
		return response.getEnd();
	}

	
}
