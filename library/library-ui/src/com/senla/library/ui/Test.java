package com.senla.library.ui;

import java.util.List;

import com.senla.library.api.comparator.book.SortBookType;
import com.senla.library.api.comparator.order.SortOrderType;
import com.senla.library.api.ui.menu.BookMenuType;
import com.senla.library.api.ui.menu.IMenu;
import com.senla.library.api.ui.menu.IMenuItem;
import com.senla.library.api.ui.menu.MainMenuType;
import com.senla.library.api.ui.menu.OrderMenuType;
import com.senla.library.api.ui.menu.RequestMenuType;
import com.senla.library.api.ui.menu.TotalMenuType;
import com.senla.library.ui.action.ExitAction;
import com.senla.library.ui.action.book.BookAddAction;
import com.senla.library.ui.action.book.BookShowAllAlphabeticallyAction;
import com.senla.library.ui.action.book.BookShowAllByDateAction;
import com.senla.library.ui.action.book.BookShowAllByPriceAction;
import com.senla.library.ui.action.book.BookShowAllByStockAction;
import com.senla.library.ui.action.book.BookShowDescriptionAction;
import com.senla.library.ui.action.book.BookShowQueryAction;
import com.senla.library.ui.action.book.BookShowUnsoldAction;
import com.senla.library.ui.action.book.BookWriteOffAction;
import com.senla.library.ui.action.order.OrderAddAction;
import com.senla.library.ui.action.order.OrderAddBookAction;
import com.senla.library.ui.action.order.OrderCancelAction;
import com.senla.library.ui.action.order.OrderCompleteAction;
import com.senla.library.ui.action.order.OrderShowAllByDateAction;
import com.senla.library.ui.action.order.OrderShowAllByPriceAction;
import com.senla.library.ui.action.order.OrderShowAllByStatusAction;
import com.senla.library.ui.action.order.OrderShowCompletedQuantityAction;
import com.senla.library.ui.action.order.OrderShowDataPeriodAction;
import com.senla.library.ui.action.order.OrderShowDetailsAction;
import com.senla.library.ui.action.request.RequestAddAction;
import com.senla.library.ui.action.total.TotalShowIncomeAction;
import com.senla.library.ui.menu.Menu;
import com.senla.library.ui.menu.MenuItem;

public class Test {

	public static void addItemToMenu(IMenu menu, IMenuItem... newMenuItems) {
		List<IMenuItem> menuItems = menu.getMenuItems();
		for (IMenuItem menuItem : newMenuItems)
			menuItems.add(menuItem);
	}

	public static void main(String args[]) {
		
		IMenuItem sortBook1 = new MenuItem(SortBookType.ALPHABETICALLY.toString(), null, new BookShowAllAlphabeticallyAction());
		IMenuItem sortBook2 = new MenuItem(SortBookType.BY_PRICE.toString(), null, new BookShowAllByPriceAction());
		IMenuItem sortBook3 = new MenuItem(SortBookType.BY_PUBLICATION_DATE.toString(), null, new BookShowAllByDateAction());
		IMenuItem sortBook4 = new MenuItem(SortBookType.BY_STOCK.toString(), null, new BookShowAllByStockAction());		
		IMenu menuSortBook = new Menu("Sort books by:");
		addItemToMenu(menuSortBook, sortBook1, sortBook2, sortBook3, sortBook4);

		IMenuItem book1 = new MenuItem(BookMenuType.ADD.toString(), null, new BookAddAction());
		IMenuItem book2 = new MenuItem(BookMenuType.WRITE_OFF.toString(), null, new BookWriteOffAction());
		IMenuItem book3 = new MenuItem(BookMenuType.SHOW_ALL.toString(), menuSortBook, null);
		IMenuItem book4 = new MenuItem(BookMenuType.SHOW_UNSOLD.toString(), null, new BookShowUnsoldAction());
		IMenuItem book5 = new MenuItem(BookMenuType.SHOW_QUERY.toString(), null, new BookShowQueryAction());
		IMenuItem book6 = new MenuItem(BookMenuType.SHOW_DESCRIPTION.toString(), null, new BookShowDescriptionAction());
		IMenu menuBook = new Menu("Book menu:");
		addItemToMenu(menuBook, book1, book2, book3, book4, book5, book6);

		IMenuItem sortOrder1 = new MenuItem(SortOrderType.BY_EXECUTION_DATE.toString(), null, new OrderShowAllByDateAction());
		IMenuItem sortOrder2 = new MenuItem(SortOrderType.BY_PRICE.toString(), null, new OrderShowAllByPriceAction());
		IMenuItem sortOrder3 = new MenuItem(SortOrderType.BY_STATUS.toString(), null, new OrderShowAllByStatusAction());		
		IMenu menuSortOrder = new Menu("Sort orders by:");
		addItemToMenu(menuSortOrder, sortOrder1, sortOrder2, sortOrder3);		
		
		IMenuItem order1 = new MenuItem(OrderMenuType.ADD.toString(), null, new OrderAddAction());
		IMenuItem order2 = new MenuItem(OrderMenuType.ADD_BOOK_TO_ORDER.toString(), null, new OrderAddBookAction());
		IMenuItem order3 = new MenuItem(OrderMenuType.COMPLETE.toString(), null, new OrderCompleteAction());
		IMenuItem order4 = new MenuItem(OrderMenuType.CANCEL.toString(), null, new OrderCancelAction());
		IMenuItem order5 = new MenuItem(OrderMenuType.SHOW_DETAILS.toString(), null, new OrderShowDetailsAction());
		IMenuItem order6 = new MenuItem(OrderMenuType.SHOW_ALL.toString(), menuSortOrder, null);
		IMenuItem order7 = new MenuItem(OrderMenuType.SHOW_FOR_DATE_PERIOD.toString(), null, new OrderShowDataPeriodAction());
		IMenuItem order8 = new MenuItem(OrderMenuType.SHOW_COMPLETED_QUANTITY.toString(), null, new OrderShowCompletedQuantityAction());	
		IMenu menuOrder = new Menu("Order menu:");
		addItemToMenu(menuOrder, order1, order2, order3, order4, order5, order6, order7, order8);
		
		IMenuItem request1 = new MenuItem(RequestMenuType.ADD.toString(), null, new RequestAddAction());		
		IMenu menuRequest = new Menu("Request Menu:");
		addItemToMenu(menuRequest, request1);
		
		IMenuItem total1 = new MenuItem(TotalMenuType.SHOW_TOTAL_INCOME.toString(), null, new TotalShowIncomeAction());
		IMenu menuTotal = new Menu("Total menu:");
		addItemToMenu(menuTotal, total1);
		
		IMenuItem main1 = new MenuItem(MainMenuType.BOOK.toString(), menuBook, null);
		IMenuItem main2 = new MenuItem(MainMenuType.ORDER.toString(), menuOrder, null);
		IMenuItem main3 = new MenuItem(MainMenuType.REQUEST.toString(), menuRequest, null);
		IMenuItem main4 = new MenuItem(MainMenuType.TOTAL.toString(), menuTotal, null);
		IMenuItem main5 = new MenuItem(MainMenuType.EXIT.toString(), null, new ExitAction());

		IMenu menuMain = new Menu("Main menu:");
		addItemToMenu(menuMain, main1, main2, main3, main4, main5);

		new MenuController(menuMain).run();

	}

}
