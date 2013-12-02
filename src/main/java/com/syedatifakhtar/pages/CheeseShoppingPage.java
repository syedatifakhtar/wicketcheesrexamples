package com.syedatifakhtar.pages;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.syedatifakhtar.BasePage;
import com.syedatifakhtar.DAO.CheesrCart;
import com.syedatifakhtar.DAO.CheeseOrderService;
import com.syedatifakhtar.DAO.CheeseService;
import com.syedatifakhtar.DAO.CheesrCartActionListener;
import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.panel.ShoppingPanel;
import com.syedatifakhtar.session.CheesrSession;

public class CheeseShoppingPage extends BasePage{

	@SpringBean(name="cheeseService")
	CheeseService cheeseService;
	
	@SpringBean(name="cheeseOrderService")
	CheeseOrderService cheeseOrderService;
	
	private RepeatingView shoppingItemRepeater;
	private WebMarkupContainer shoppingItemRepeaterContainer;
	private AjaxButton checkoutButton;
	private Form<Void> checkoutForm;
	private CheesrCart cheeseCartService;
	
	private CheesrCartActionListener	cheesrCartOrderListner	=	new CheesrCartActionListener() {
		@Override
		public void removeCheese(Cheese cheese) {
			cheeseCartService.removeFromCart(cheese);
			System.out.println("Removing cheese : " + cheese.getName());
		}
		
		@Override
		public void addCheese(Cheese cheese, Integer quantity) {
			cheeseCartService.addToCart(cheese, quantity);
			System.out.println("Added cheese : " + cheese.getName() + " \t Quantity:" + quantity.longValue());
		}
	}; 
	public CheeseShoppingPage(PageParameters pageParams) {
		super(pageParams);
		Injector.get().inject(this);
		init();
		attachComponents();
		CheesrSession cheesrSession	=	(CheesrSession)getSession();
		this.cheeseCartService=cheesrSession.getCart();
	}
	
	private void init() {
		shoppingItemRepeaterContainer	=	new WebMarkupContainer("shoppingItemRepeaterContainer");
		shoppingItemRepeaterContainer.setOutputMarkupId(true);
		shoppingItemRepeater	=	new RepeatingView("shoppingItemRepeater");
		for(Cheese cheese:cheeseService.findAll()){
			ShoppingPanel shoppingPanel	=new ShoppingPanel(shoppingItemRepeater.newChildId(), cheese);
			shoppingPanel.attachListener(cheesrCartOrderListner);
			shoppingItemRepeater.add(shoppingPanel);
			checkoutForm	=	new Form("checkoutForm");
		checkoutButton	=	new AjaxButton("checkoutButton",checkoutForm) {
			protected void onSubmit(AjaxRequestTarget target,Form<?> form) {
				System.out.println("---------------------Printing Cheese Cart---------------------------");
				Map<Cheese,Integer> cheeseCart	=	cheeseCartService.getCart();
				for(Cheese cheese:cheeseCart.keySet()) {
					System.out.println(cheese.getName() + " \t Quantity: " + cheeseCart.get(cheese));
				}
				cheeseOrderService.saveOrder(cheeseCartService.getOrder(), cheeseCart);
			
			};
			};
		};
	}
	private void attachComponents() {
		shoppingItemRepeaterContainer.add(shoppingItemRepeater);
		checkoutForm.add(checkoutButton);
		add(checkoutForm);
		add(shoppingItemRepeaterContainer);
	}

}
