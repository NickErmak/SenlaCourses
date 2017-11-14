package com.senla.ui;

import java.util.Scanner;

public class MenuController {
	
	private Builder builder;
	private Navigator navigator;
	private Scanner scanner;
	
	public MenuController(Menu rootMenu) {
		builder = new Builder(rootMenu);
		navigator = new Navigator(rootMenu);
		scanner = new Scanner(System.in);
	}
	
	public void run() {
		navigator.printMenu();
		navigator.navigate(scanner.nextInt());
		run();
	}	
	
}
