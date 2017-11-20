package com.senla.library.ui;

import com.senla.library.api.ui.menu.IMenu;

public class MenuController {

	private Builder builder;
	private Navigator navigator;

	public MenuController(IMenu rootMenu) {
		navigator = new Navigator(rootMenu);
		builder = new Builder(rootMenu, navigator);
	}

	public void run() {
		navigator.printMenu();
		if (!builder.buildMenu())
			run();
	}
}
