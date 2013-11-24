package com.syedatifakhtar;

import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IResourceSettings;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.file.IResourceFinder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.syedatifakhtar.pages.HomePage;
import com.syedatifakhtar.service.MessengerService;
import com.syedatifakhtar.utils.MountedMapperWithoutPageComponentInfo;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.syedatifakhtar.Start#main(String[])
 */
@SuppressWarnings("all")
public class WicketApplication extends WebApplication {

	private SessionFactory hibernateSessionFactory;

	public SessionFactory getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}

	public void setHibernateSessionFactory(
			SessionFactory hibernateSessionFactory) {
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	@SpringBean(name = "messengerService")
	public MessengerService messengerService;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// IMPORTANT - Installs the SpringComponentInjector - Allows POJOs to be
		// inserted using Injector.get().inject(this)
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));

		// add your configuration here
		IResourceSettings resourceSettings = getResourceSettings();
		WebApplicationPath webAppPath = new WebApplicationPath(
				getServletContext(), "/WEB-INF/markup/");
		IResourceFinder pageResources = webAppPath;
		System.out.println("Using resource path: " + webAppPath.toString());
		resourceSettings.getResourceFinders().add(pageResources);
		mount(new MountedMapperWithoutPageComponentInfo("/", HomePage.class));
		
		try {
			initHibernate();
		} catch (Exception e) {
			System.out.println("Hibernate could not be initialized!");
			e.printStackTrace();
		}
	}

	public MessengerService getMessengerService() {
		return messengerService;
	}

	public void setMessengerService(MessengerService messengerService) {
		this.messengerService = messengerService;
	}

	private void initHibernate() throws Exception {
		hibernateSessionFactory = new Configuration().configure("/resources/hibernate.cfg.xml") // Use
																	// bibernate.cfg.xml
																	// to
																	// configure
																	// settings
				.buildSessionFactory();
	}

	private void destroyHibernateFactory() {
		if (hibernateSessionFactory != null) {
			hibernateSessionFactory.close();
		}
	}

	public Session requestNewDatabaseSession() {
		Session session = hibernateSessionFactory.openSession();
		return session;
	}
}
