package com.senla.library.ui.menu;

import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.IMenu;
import com.senla.library.api.ui.menu.IMenuItem;

public class MenuItem implements IMenuItem{
	private String title;
	private IMenu nextMenu;
	private IAction action;

	public MenuItem(String title, IMenu nextMenu, IAction action) {
		this.title = title;
		this.nextMenu = nextMenu;
		this.action = action;
	}

	public IQuery doAction() {
		if (action != null)
			return action.execute();
		else
			return null;
	}

	public IMenu getNextMenu() {
		return nextMenu;
	}

	public String toString() {
		return title;
	}
}
