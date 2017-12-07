package com.senla.library.ui;

import org.apache.log4j.xml.DOMConfigurator;

import com.senla.library.api.ui.menu.IMenu;

public class MenuController {
	private static IMenu rootMenu = MenuCreator.createMenu();
	private Builder builder;
	private Navigator navigator;

	static {
		DOMConfigurator.configure("resources/log4j.xml");
	}

	public MenuController() {
		navigator = new Navigator(rootMenu);
		builder = new Builder(rootMenu, navigator);
	}

	public void run() {
		navigator.printMenu();
		if (!builder.buildMenu())
			run();
	}
}
