package com.senla.library.ui.menu;

import com.senla.library.ui.IMenu;

public class Menu implements IMenu{
	
	private String name;
	private MenuItem[] menuItems;
	
	public Menu(String name, MenuItem[] menuItems) {
		this.name = name;
		this.menuItems = menuItems;
	}
	
	public String getName() {
		return name;
	}
		
	public MenuItem[] getMenuItems() {
		return menuItems;
	}
	
}
