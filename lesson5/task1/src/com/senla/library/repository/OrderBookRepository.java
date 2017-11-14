package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.util.ArrayHandler;

public class OrderBookRepository {

	private TextFileWorker textFileWorker;
	private List<OrderBookRelation> relationList;

	public OrderBookRepository(String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		relationList = new ArrayList<>();
		readData();
	}

	public void addRelation(OrderBookRelation relation) {
		relationList.add(relation);
	}

	public List<OrderBookRelation> getRelations() {
		return relationList;
	}

	public void readData() {
		for (String relation : textFileWorker.readFromFile())
			addRelation(new OrderBookRelation(relation.split("  ")));
	}

	public void saveData() {
		textFileWorker.writeToFile(ArrayHandler.getStringArray(relationList));
	}

}
