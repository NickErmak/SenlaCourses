package com.senla.library.ui;

import com.senla.library.ui.action.ExitAction;
import com.senla.library.ui.action.book.BookAddAction;
import com.senla.library.ui.action.book.BookShowAllAction;
import com.senla.library.ui.action.book.BookShowDescriptionAction;
import com.senla.library.ui.action.book.BookShowQueryAction;
import com.senla.library.ui.action.book.BookShowUnsoldAction;
import com.senla.library.ui.action.book.BookWriteOffAction;
import com.senla.library.ui.menu.BookMenuType;
import com.senla.library.ui.menu.MainMenuType;
import com.senla.library.ui.menu.Menu;
import com.senla.library.ui.menu.MenuItem;

public class Test {

	public static void main(String args[]) {

		MenuItem book1 = new MenuItem(BookMenuType.Add.toString(), null, new BookAddAction());
		MenuItem book2 = new MenuItem(BookMenuType.WriteOff.toString(), null, new BookWriteOffAction());
		MenuItem book3 = new MenuItem(BookMenuType.ShowAll.toString(), null, new BookShowAllAction());
		MenuItem book4 = new MenuItem(BookMenuType.ShowUnsold.toString(), null, new BookShowUnsoldAction());
		MenuItem book5 = new MenuItem(BookMenuType.ShowQuery.toString(), null, new BookShowQueryAction());
		MenuItem book6 = new MenuItem(BookMenuType.ShowDescription.toString(), null, new BookShowDescriptionAction());
		
		Menu menuBook = new Menu("Book menu:", new MenuItem[] {book1, book2, book3, book4, book5, book6});		
		
		MenuItem main1 = new MenuItem(MainMenuType.Book.toString(), menuBook, null);
		MenuItem main2 = new MenuItem(MainMenuType.Order.toString(), null, null);
		MenuItem main3 = new MenuItem(MainMenuType.Request.toString(), null, null);
		MenuItem main4 = new MenuItem(MainMenuType.Total.toString(), null, null);
		MenuItem main5 = new MenuItem(MainMenuType.Exit.toString(), null, new ExitAction());
		
		Menu menuMain = new Menu("Main menu:", new MenuItem[] {main1, main2, main3, main4, main5});

		new MenuController(menuMain).run();

	}

}
