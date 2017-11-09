package com.senla.library.repository;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.util.ArrayHandler;
import com.senla.library.util.FileWorker;

public class OrderBookRepository {

	private OrderBookRelation[] relationList;
	private TextFileWorker textFileWorker;

	public OrderBookRepository(int maxBookOrder, String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		relationList = new OrderBookRelation[maxBookOrder];
		readData();
	}

	public void addRelation(OrderBookRelation relation) {
		relationList[ArrayHandler.getFreeCellIndex(relationList)] = relation;
	}

	public OrderBookRelation[] getRelations() {
		return relationList;
	}

	public void readData() {
		FileWorker.readData(textFileWorker, relationList, new OrderBookRelation());
	}

	public void saveData() {
		FileWorker.saveData(textFileWorker, relationList);
	}

}
