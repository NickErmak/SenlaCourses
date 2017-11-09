package com.senla.library.manager;

import com.senla.library.entity.OrderBookRelation;
import com.senla.library.repository.OrderBookRepository;

public class OrderBookManager {

	private final OrderBookRepository orderBookRep;

	public OrderBookManager(int maxOrderBook, String filePath) {
		orderBookRep = new OrderBookRepository(maxOrderBook, filePath);
	}

	public OrderBookRelation[] getRelations() {
		return orderBookRep.getRelations();
	}

	public void addOrderBookRelation(OrderBookRelation relation) {
		orderBookRep.addRelation(relation);
	}

	public void save() {
		orderBookRep.saveData();
	}

}
