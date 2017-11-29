package com.senla.library.manager;

import java.util.List;

import com.senla.library.api.bean.IOrderBookRelation;
import com.senla.library.api.exception.NoSuchIdException;
import com.senla.library.repository.OrderBookRepository;

public class OrderBookManager {

	private final OrderBookRepository orderBookRepository;

	public OrderBookManager() throws NoSuchIdException {
		orderBookRepository = OrderBookRepository.getInstance();
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
