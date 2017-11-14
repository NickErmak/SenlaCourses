package com.senla.library.manager;

import java.util.List;

import com.senla.library.entity.OrderBookRelation;
import com.senla.library.repository.OrderBookRepository;

public class OrderBookManager {

	private final OrderBookRepository orderBookRep;

	public OrderBookManager(String filePath) {
		orderBookRep = new OrderBookRepository(filePath);
	}

	public List<OrderBookRelation> getRelations() {
		return orderBookRep.getRelations();
	}

	public void addOrderBookRelation(OrderBookRelation relation) {
		orderBookRep.addRelation(relation);
	}

	public void save() {
		orderBookRep.saveData();
	}

}
