package com.syedatifakhtar.DAO;

import java.util.List;

import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.model.Order;

public interface OrderDAO {

	public List<Order> findall();
	public Order getOrder(long id);
	public long saveOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(Order order);
}
