package com.senla.library.manager;

import java.util.List;

import com.senla.library.bean.IOrderBookRelation;
import com.senla.library.repository.OrderBookRepository;

public class OrderBookManager {

	private final OrderBookRepository orderBookRepository;

	public OrderBookManager(String filePath) {
		orderBookRepository = new OrderBookRepository(filePath);
	}

	public List<IOrderBookRelation> getRelations() {
		return orderBookRepository.getRelations();
	}

	public IOrderBookRelation addOrderBookRelation(int orderId, int bookId) {
		return orderBookRepository.addRelation(orderId, bookId);
	}

	public void save() {
		orderBookRepository.saveData();
	}

}
