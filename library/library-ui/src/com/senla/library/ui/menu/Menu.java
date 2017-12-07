package com.senla.library.ui.menu;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.ui.menu.IMenu;
import com.senla.library.api.ui.menu.IMenuItem;

public class Menu implements IMenu{	
	private String name;
	private List<IMenuItem> menuItems;
	
	public Menu(String name) {
		menuItems = new ArrayList<>();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
		
	public List<IMenuItem> getMenuItems() {
		return menuItems;
	}	
}
