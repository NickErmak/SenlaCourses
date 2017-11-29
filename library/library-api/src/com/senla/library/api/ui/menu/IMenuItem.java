package com.senla.library.api.ui.menu;

import com.senla.library.api.transmitter.query.IQuery;

public interface IMenuItem {
	
	public IQuery doAction();
	public IMenu getNextMenu();
}
