package com.syedatifakhtar.DAO;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.syedatifakhtar.model.Order;

public class OrderDAOImpl implements OrderDAO{

	
	@SpringBean(name="mySessionFactory")
	private SessionFactory mySessionFactory;
	
	@Transactional
	@Override
	public List<Order> findall() {
		Session	session = mySessionFactory.getCurrentSession();
		List orders	=	session.createQuery("from Order").list();
		return (List<Order>)orders;
	}

	@Override
	public Order getOrder(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long saveOrder(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	public SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}

	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

}
