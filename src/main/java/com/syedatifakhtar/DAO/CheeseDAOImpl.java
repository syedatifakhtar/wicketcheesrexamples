package com.syedatifakhtar.DAO;

import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.syedatifakhtar.model.Cheese;

@SuppressWarnings({"unchecked", "rawtypes"})
public class CheeseDAOImpl implements CheeseDAO{
	
	@SpringBean(name="mySessionFactory")
	private SessionFactory mySessionFactory;
	
	@Transactional
	@Override
	public List<Cheese> findAll() {
		if(mySessionFactory==null) {
			System.out.println("SessionFactory is null!!!");
		}
		Session session	=	mySessionFactory.getCurrentSession();
		List cheeses= session.createQuery("from Cheese").list();
		return (List<Cheese>)cheeses;
	}

	public SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}

	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

}
