package com.syedatifakhtar.DAO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.model.CheeseOrder;
import com.syedatifakhtar.model.Order;

public class CheeseOrderServiceImpl implements CheeseOrderService{

	private OrderDAO orderDAO;
	private CheeseDAO cheeseDAO;
	
	
	public CheeseDAO getCheeseDAO() {
		return cheeseDAO;
	}

	public void setCheeseDAO(CheeseDAO cheeseDAO) {
		this.cheeseDAO = cheeseDAO;
	}

	
	@Transactional
	@Override
	public List<Order> findall() {
		// TODO Auto-generated method stub
		return orderDAO.findall();
	}

	@Transactional
	@Override
	public Order getOrder(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public long saveOrder(Order order,Map<Cheese,Integer> cheeseQuantityMap) {
		Set<CheeseOrder> cheeseOrders	=	order.getCheeseOrder();
		CheeseOrder cheeseOrder;
		for(Cheese cheese: cheeseQuantityMap.keySet()) {
			System.out.println("Savin cheese order for " + order.getPersonName() + " Cheese: " + cheese.getName() + " QUantity: " + cheeseQuantityMap.get(cheese));
			cheeseOrder	=	new CheeseOrder();
			cheeseOrder.setOrder(order);
			cheeseOrder.setCheese(cheese);
			cheeseOrder.setQuantity(cheeseQuantityMap.get(cheese));
			cheeseOrders.add(cheeseOrder);
		}
		long orderid= orderDAO.saveOrder(order);
		
		System.out.println("OrderId--->" + orderid);
		return orderid;
	}

	@Transactional
	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
}
