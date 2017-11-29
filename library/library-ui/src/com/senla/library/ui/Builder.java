package com.senla.library.ui;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.transmitter.ITransmitter;
import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;
import com.senla.library.api.ui.ConsoleMessage;
import com.senla.library.api.ui.menu.IMenu;
import com.senla.library.api.ui.menu.IMenuItem;
import com.senla.library.ui.transmitter.Transmitter;
import com.senla.library.util.Printer;

public class Builder {

	private ITransmitter transmitter;
	private IMenu currentMenu;
	private Navigator navigator;
	private Scanner scanner;
	private Stack<IMenu> previousMenu;

	public Builder(IMenu rootMenu, Navigator navigator) {
		currentMenu = rootMenu;
		this.navigator = navigator;
		transmitter = Transmitter.getInstance();
		scanner = new Scanner(System.in);
		previousMenu = new Stack<>();
	}

	public boolean buildMenu() {
		try {
			Integer indexInput = scanner.nextInt();
			if (indexInput == 0) {
				currentMenu = previousMenu.pop();
				if (previousMenu.empty())
					notifyNavigator(ConsoleMessage.START);
				else
					notifyNavigator(ConsoleMessage.NO_MESSAGE);
				return false;
			}
			IMenuItem menuItem = currentMenu.getMenuItems().get(indexInput - 1);
			if (menuItem.doAction() != null) {
				IQuery query = menuItem.doAction();
				completeQuery(query);
				IResponse response = transmitter.sendQuery(query);
				Printer.print(response);
				scanner.next();
				if (response.isExit())
					scanner.close();
				return response.isExit();
			} else {
				previousMenu.push(currentMenu);
				currentMenu = menuItem.getNextMenu();
				notifyNavigator(ConsoleMessage.NO_MESSAGE);
				return false;
			}

		} catch (NoSuchElementException e) {
			scanner.next();
			notifyNavigator(ConsoleMessage.ERROR_INCORRECT_INPUT);
			return false;
		} catch (IndexOutOfBoundsException | EmptyStackException e) {
			notifyNavigator(ConsoleMessage.ERROR_NO_SUCH_ITEM);
			return false;
		} catch (NoSuchIdException e) {
			notifyNavigator(ConsoleMessage.ERROR_NO_SUCH_ID);
			return false;
		}
	}

	private void notifyNavigator(ConsoleMessage consoleMessage) {
		navigator.setConsoleMessage(consoleMessage);
		navigator.navigate(currentMenu);
	}
	
	private void completeQuery(IQuery query) {
		if (query.getActionInfo().containsKey("inputMessage")) {			
			Printer.print(query);
			String input = scanner.next();
			query.putInput(input);	
		}
			
	}
}
