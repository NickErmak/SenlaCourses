package com.senla.library.ui;

import java.util.EmptyStackException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.senla.library.api.transmitter.ITransmitter;
import com.senla.library.api.transmitter.query.IQuery;
import com.senla.library.api.transmitter.response.IResponse;
import com.senla.library.api.ui.ConsoleMessage;
import com.senla.library.api.ui.IAction;
import com.senla.library.api.ui.menu.IMenu;
import com.senla.library.api.ui.menu.IMenuItem;
import com.senla.library.ui.transmitter.Transmitter;
import com.senla.library.util.Printer;

public class Builder {
	private static Logger logger = Logger.getLogger(Builder.class);
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

	private void notifyNavigator(ConsoleMessage consoleMessage) {
		navigator.setConsoleMessage(consoleMessage);
		navigator.navigate(currentMenu);
	}

	private void completeQuery(IQuery query) {
		Map<String, Object> actionInfo = query.getActionInfo();
		if (actionInfo.containsKey("inputMessage")) {
			if (actionInfo.containsKey("inputAction")) {
				IAction inputAction = (IAction) query.getActionInfo().get("inputAction");
				IResponse inputResponse = transmitter.sendQuery(inputAction.execute());
				Printer.print(inputResponse);
			}
			Printer.print(query);
			query.putInput(scanner.next() + scanner.nextLine());
		}
	}

	public boolean buildMenu() {
		try {
			Integer indexInput = scanner.nextInt();
			if (indexInput == 0) {
				currentMenu = previousMenu.pop();
				if (previousMenu.empty()) {
					notifyNavigator(ConsoleMessage.START);
				} else {
					notifyNavigator(ConsoleMessage.NO_MESSAGE);
				}
				return false;
			}
			IMenuItem menuItem = currentMenu.getMenuItems().get(indexInput - 1);
			if (menuItem.doAction() != null) {
				IQuery query = menuItem.doAction();
				completeQuery(query);
				IResponse response = transmitter.sendQuery(query);
				Printer.print(response);
				if (response.isExit()) {
					scanner.close();
				} else {
					scanner.next();
				}
				return response.isExit();
			} else {
				previousMenu.push(currentMenu);
				currentMenu = menuItem.getNextMenu();
				notifyNavigator(ConsoleMessage.NO_MESSAGE);
				return false;
			}
		} catch (NoSuchElementException | NumberFormatException e) {
			notifyNavigator(ConsoleMessage.ERROR_INCORRECT_INPUT);
			scanner.next();
			logger.error(e);
			return false;
		} catch (IndexOutOfBoundsException | EmptyStackException e) {
			notifyNavigator(ConsoleMessage.ERROR_NO_SUCH_ITEM);
			logger.error(e);
			return false;
		}
	}
}
