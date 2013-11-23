package com.syedatifakhtar.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;

import com.syedatifakhtar.pages.HomePage;

public class Menu extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MenuItem> menuItems;
	private RepeatingView repeatingView;
	public Menu(String id) {
		super(id);
		this.setOutputMarkupId(true);
		repeatingView	=	new RepeatingView("classyMenuRepeater");
		createMenuItems();
		addMenuItems();
		add(repeatingView);
	}

	private void createMenuItems() {
		menuItems	=	new ArrayList<MenuItem>();
		MenuItem menuItem	=	new MenuItem("Cheesr", HomePage.class);
		menuItems.add(menuItem);
	}
	
	@SuppressWarnings("unchecked")
	private void addMenuItems() {
		for (final MenuItem menuItem : menuItems) {
			System.out.println("Menu Item--->" + menuItem.getName());
			WebMarkupContainer webMarkupContainer	=	new WebMarkupContainer(repeatingView.newChildId());
			StatelessLink menuLink	=	new StatelessLink("menuLink") {
				@Override
				public void onClick() {
					setResponsePage(menuItem.getRedirectToPage());
					
				}
			};
			menuLink.add(new Label("menuItemLabel",new PropertyModel<MenuItem>(menuItem, "name")));
			webMarkupContainer.add(menuLink);
			repeatingView.add(webMarkupContainer);
			
		}
		repeatingView.setRenderBodyOnly(false);
	}
	
}
