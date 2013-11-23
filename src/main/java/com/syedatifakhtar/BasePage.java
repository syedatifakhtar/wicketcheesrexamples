package com.syedatifakhtar;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.syedatifakhtar.component.Menu;

public abstract class BasePage extends WebPage{

	
	public BasePage(PageParameters pageParams) {
		super(pageParams);
		add(new Menu("classyMenu"));
	}
	
}
