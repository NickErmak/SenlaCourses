package com.senla.ui;

public class Menu {
	
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
