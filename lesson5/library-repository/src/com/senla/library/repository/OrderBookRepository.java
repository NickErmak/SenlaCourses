package com.senla.library.repository;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.api.repository.IOrderBookRepository;
import com.senla.library.entity.OrderBookRelation;
import com.senla.library.util.ArrayHandler;

public class OrderBookRepository implements IOrderBookRepository{

	private static OrderBookRepository instance;
	private static final String FILE_PATH = "data/relation.txt";
	private TextFileWorker textFileWorker;
	private List<IOrderBookRelation> relationList;

	private OrderBookRepository() {
		textFileWorker = new TextFileWorker(FILE_PATH);
		relationList = new ArrayList<>();
		readData();
	}
	
	public static OrderBookRepository getInstance() throws NoSuchIdException {
		if (instance == null)
			instance = new OrderBookRepository();
		return instance;
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
