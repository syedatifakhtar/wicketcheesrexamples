package com.syedatifakhtar.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.syedatifakhtar.BasePage;
import com.syedatifakhtar.WicketApplication;
import com.syedatifakhtar.DAO.CheeseDAO;
import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.panel.CheeseActionPanel;

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
	private String messageFromService;
	private final RepeatingView cheeseRepeater;
	private final List<Cheese> cheeses;
	private final AjaxFallbackLink<Void> addCheeseAjaxLink;
	private final WebMarkupContainer cheeseRepeaterContainer;
	
	@SpringBean(name="cheeseDAO")
	private CheeseDAO cheeseDAO;
	
	
	public HomePage(final PageParameters parameters) {
		super(parameters);
		Injector.get().inject(this);
		Injector.get().inject(this);
		WicketApplication myApplication = (WicketApplication) getApplication();
		messageFromService = myApplication.getMessengerService().getMessage();
		System.out.println(messageFromService);
		add(new Label("howdymessage", messageFromService));
		cheeses = generateDummyCheeseList();
		cheeseRepeater = new RepeatingView("cheeseRepeater");
		for (Cheese cheese : cheeses) {
			cheeseRepeater.add(new CheeseActionPanel(cheeseRepeater
					.newChildId(), cheese));
		}
		cheeseRepeater.setOutputMarkupId(true);
		cheeseRepeaterContainer = new WebMarkupContainer(
				"cheeseRepeaterContainer"); //A hack to allow the repeater to repaint itself
		cheeseRepeaterContainer.setOutputMarkupId(true);
		
		List<Cheese> cheeses	=	cheeseDAO.findAll();
		for(Cheese cheese : cheeses) {
			System.out.println("Cheese (" + cheese.getName() + " ," + cheese.getDescription() + ")");
		}
		addCheeseAjaxLink = new AjaxFallbackLink<Void>(
				"addCheeseAjaxFallbackLink") {
			@Override
			public void onClick(AjaxRequestTarget requestTarget) {
				Cheese addedCheese = new Cheese();
				CheeseActionPanel cheeseActionPanel = new CheeseActionPanel(
						cheeseRepeater.newChildId(), addedCheese,
						CheeseActionPanel.Mode.CREATE);
				cheeseRepeater.add(cheeseActionPanel);
				requestTarget.add(cheeseRepeaterContainer);
			}
		};

		add(addCheeseAjaxLink);
		cheeseRepeaterContainer.add(cheeseRepeater);
		add(cheeseRepeaterContainer);

	}

	public List<Cheese> generateDummyCheeseList() {
		List<Cheese> dummyCheeseList;
		dummyCheeseList = new ArrayList<Cheese>();
		Cheese dummyCheese = new Cheese();
		dummyCheese.setName("Gouda");
		dummyCheese.setDescription("Most popular - your basic fare");
		dummyCheeseList.add(dummyCheese);
		dummyCheese = new Cheese();
		dummyCheese.setName("Blue Cheese");
		dummyCheese.setDescription("Its blue its tasty!");
		dummyCheeseList.add(dummyCheese);
		dummyCheese = new Cheese();
		dummyCheese.setName("Cheddar");
		dummyCheese.setDescription("Makes your pizza better!");
		dummyCheeseList.add(dummyCheese);
		dummyCheese = new Cheese();
		dummyCheese.setName("Mozzarella");
		dummyCheese.setDescription("FRESH!");
		dummyCheeseList.add(dummyCheese);
		return dummyCheeseList;
	}

	public CheeseDAO getCheeseDAO() {
		return cheeseDAO;
	}

	public void setCheeseDAO(CheeseDAO cheeseDAO) {
		this.cheeseDAO = cheeseDAO;
	}
	
	
}
