package com.senla.library.repository;

import com.senla.library.api.annotation.di.Inject;
import com.senla.library.api.repository.IBookRepository;
import com.senla.library.api.repository.IOrderRepository;
import com.senla.library.api.repository.IRequestRepository;

public class RepositoryShell {	
	
	@Inject("com.senla.library.repository")
	private static IBookRepository bookRepository;
	
	@Inject("com.senla.library.repository")
	private static IOrderRepository orderRepository;
	
	@Inject("com.senla.library.repository")
	private static IRequestRepository requestRepository;
	
	public static IBookRepository getBookRepository() {
		return bookRepository;
	}
	
	public static IOrderRepository getOrderRepository() {
		return orderRepository;
	}

	public static IRequestRepository getRequestRepository() {
		return requestRepository;
	}
}
