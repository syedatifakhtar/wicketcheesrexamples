package com.syedatifakhtar.DAO;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.SessionFactory;

import com.syedatifakhtar.model.Order;

public interface OrderDAO {

	public List<Order> findall();
	public Order getOrder(long id);
	public long saveOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(Order order);

}