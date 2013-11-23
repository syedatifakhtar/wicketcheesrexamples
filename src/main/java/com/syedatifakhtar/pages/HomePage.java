package com.syedatifakhtar.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.syedatifakhtar.BasePage;
import com.syedatifakhtar.WicketApplication;
import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.panel.CheeseActionPanel;

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
	private String messageFromService;
	private final RepeatingView cheeseRepeater;
	private final List<Cheese> cheeses;
	private final AjaxFallbackLink<Void> addCheeseAjaxLink;
	private final WebMarkupContainer cheeseRepeaterContainer;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		WicketApplication myApplication = (WicketApplication) getApplication();
		messageFromService = myApplication.getMessengerService().getMessage();
		System.out.println(messageFromService);
		add(new Label("howdymessage", messageFromService));
		cheeses = generateDummyCheeseList();
		cheeseRepeater = new RepeatingView("cheeseRepeater");
		for (Cheese cheese : cheeses) {
			cheeseRepeater.add(new CheeseActionPanel(cheeseRepeater.newChildId(), cheese));
		}
		cheeseRepeater.setOutputMarkupId(true);
		cheeseRepeaterContainer	=	new WebMarkupContainer("cheeseRepeaterContainer");
		cheeseRepeaterContainer.setOutputMarkupId(true);
		addCheeseAjaxLink	=	new AjaxFallbackLink<Void>("addCheeseAjaxFallbackLink") {
			@Override
			public void onClick(AjaxRequestTarget requestTarget) {
				Cheese addedCheese	=	new Cheese();
				CheeseActionPanel cheeseActionPanel	=new CheeseActionPanel(cheeseRepeater.newChildId(), addedCheese,CheeseActionPanel.Mode.CREATE);	
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
}