package com.senla.library.ui;

import com.senla.library.api.ui.ConsoleMessage;
import com.senla.library.api.ui.menu.IMenu;
import com.senla.library.util.Printer;

public class Navigator {	
	private IMenu currentMenu;
	private ConsoleMessage consoleMessage;

	public Navigator(IMenu rootMenu) {		
		currentMenu = rootMenu;
		consoleMessage = ConsoleMessage.START;		
	}	

	public void navigate(IMenu menu) {			
		currentMenu = menu;		
	}
	
	public void printMenu() {
		Printer.print(currentMenu, consoleMessage);
	}

	public void setConsoleMessage(ConsoleMessage consoleMessage) {
		this.consoleMessage = consoleMessage;
	}
}
