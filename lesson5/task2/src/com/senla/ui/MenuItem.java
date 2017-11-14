package com.senla.ui;

public class MenuItem{
	
	private String title;
	private Menu nextMenu;
	
	public MenuItem(String title, Menu nextMenu) {
		this.title = title;
		this.nextMenu = nextMenu;
	}


	public void doAction() {
		
	}
	
	public Menu getNextMenu() {
		return nextMenu;
	}
		
	public String toString() {
		return title;
	}

	
}
