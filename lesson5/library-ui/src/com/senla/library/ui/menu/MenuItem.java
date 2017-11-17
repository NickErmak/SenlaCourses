package com.senla.library.ui.menu;

import com.senla.library.ui.IAction;
import com.senla.library.ui.IMenuItem;
import com.senla.library.ui.IQuery;

public class MenuItem implements IMenuItem{

	private String title;
	private Menu nextMenu;
	private IAction action;

	public MenuItem(String title, Menu nextMenu, IAction action) {
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

	public Menu getNextMenu() {
		return nextMenu;
	}

	public String toString() {
		return title;
	}

}
