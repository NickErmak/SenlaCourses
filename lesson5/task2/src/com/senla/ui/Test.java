package com.senla.ui;

public class Test {

	public static void main(String args[]) {

		MenuItem item2 = new MenuItem("1. add", null);
		MenuItem item3 = new MenuItem("2. delete", null);

		Menu menuOrder = new Menu("Order menu:", new MenuItem[] {item2, item3});
		
		MenuItem item1 = new MenuItem("1. order", menuOrder);
		Menu menuMain = new Menu("Main menu:", new MenuItem[] { item1 });

		new MenuController(menuMain).run();

	}

}
