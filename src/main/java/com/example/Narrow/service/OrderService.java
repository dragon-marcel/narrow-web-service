package com.example.Narrow.service;

import java.util.List;

import com.example.Narrow.model.Order;

public interface OrderService {
	List<Order> findAll();

	List<Order> getOrdersRaport(String start, String end, Long idUser);

	List<Order> findAllByIdUser(Long id);

	Order save(Order order);

	Order editOrder(Order order);

	Order findById(Long id);

	public String getNewNrOrder();

	public void delete(Order order);

	public int getCountOrdersByIdUser(Long id);

	public int getCountOrders();

	public String getProfitPerMonthByIdUser(Long id);

	public String getProfitPerMonth();
}