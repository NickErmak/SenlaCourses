package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.bean.IOrderBookRelation;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.util.ArrayHandler;

public class OrderBookRepository implements IOrderBookRepository{

	private TextFileWorker textFileWorker;
	private List<IOrderBookRelation> relationList;

	public OrderBookRepository(String filePath) {
		textFileWorker = new TextFileWorker(filePath);
		relationList = new ArrayList<>();
		readData();
	}

	public IOrderBookRelation addRelation(int orderId, int bookId) {
		IOrderBookRelation relation = new OrderBookRelation(orderId, bookId);
		relationList.add(relation);
		return relation;
	}

	public List<IOrderBookRelation> getRelations() {
		return relationList;
	}

	public void readData() {
		for (String relation : textFileWorker.readFromFile())
			relationList.add(new OrderBookRelation(relation.split("  ")));
	}

	public void saveData() {
		textFileWorker.writeToFile(ArrayHandler.getStringArray(relationList));
	}

}
