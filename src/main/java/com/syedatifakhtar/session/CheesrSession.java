package com.syedatifakhtar.session;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.syedatifakhtar.DAO.CheesrCart;
import com.syedatifakhtar.DAO.CheesrCartImpl;

public class CheesrSession extends WebSession{

	CheesrCart cheesrCart;
	public CheesrSession(Request request) {
		super(request);
		init();
	}
	
	public void init() {
		cheesrCart	=	new CheesrCartImpl();
	}
	
	public CheesrCart getCart() {
		return cheesrCart;
	}

}
