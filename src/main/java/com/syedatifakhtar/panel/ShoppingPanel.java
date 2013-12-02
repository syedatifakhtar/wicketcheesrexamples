package com.syedatifakhtar.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.syedatifakhtar.DAO.CheesrCart;
import com.syedatifakhtar.DAO.CheeseOrderService;
import com.syedatifakhtar.DAO.CheesrCartActionListener;
import com.syedatifakhtar.model.Cheese;

public class ShoppingPanel extends Panel{
	
	private Label cheeseName;
	private Label cheeseDescription;
	private TextField<Integer> cheeseQuantityTextField;
	private final Integer cheeseQuantity = 1;
	private AjaxFallbackButton addCheeseButton;
	private AjaxFallbackButton removeCheeseButton;
	private Form<Void> cheeseShoppingForm;
	private Cheese cheese;
	private boolean hasBought = false;
	private CheesrCartActionListener cheesrCartOrderListener;
	
	
	public ShoppingPanel(String id,Cheese cheese) {
		super(id);
		this.cheese=cheese;
		Injector.get().inject(this);
		init();
		attachComponents();
		toggleComponentVisibility();
	}
	
	
	private void init() {
		cheeseName = new Label("cheeseName", new PropertyModel<Cheese>(cheese,"name"));
		cheeseDescription = new Label("cheeseDescription", new PropertyModel<Cheese>(cheese,"description"));
		cheeseQuantityTextField	=	new TextField<Integer>("cheeseQuantityTextField", new PropertyModel<Integer>(this,"cheeseQuantity"));
		
		cheeseShoppingForm	=	new Form<Void>("cheeseShoppingForm");
		
		addCheeseButton	=	new AjaxFallbackButton("addCheeseButton",cheeseShoppingForm) {
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				// TODO Auto-generated method stub
				super.onSubmit(target, form);
				hasBought=true;
				toggleComponentVisibility();
				target.add(cheeseShoppingForm);
				if(cheesrCartOrderListener!=null)
					cheesrCartOrderListener.addCheese(cheese, cheeseQuantity);
			}
		};
		
		removeCheeseButton	=	new AjaxFallbackButton("removeCheeseButton",cheeseShoppingForm) {
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				if(cheesrCartOrderListener!=null) {
					cheesrCartOrderListener.removeCheese(cheese);
				}
				hasBought=false;
				toggleComponentVisibility();
				target.add(cheeseShoppingForm);
			}
		};
		
		addCheeseButton.setOutputMarkupId(true);
		cheeseQuantityTextField.setOutputMarkupId(true);
		removeCheeseButton.setOutputMarkupId(true);
		cheeseShoppingForm.setOutputMarkupId(true);
		
	}
	
	/**
	 * @return the cheeseQuantity
	 */
	protected Integer getCheeseQuantity() {
		return cheeseQuantity;
	}


	private void attachComponents() {
		cheeseShoppingForm.add(cheeseName);
		cheeseShoppingForm.add(cheeseDescription);
		cheeseShoppingForm.add(cheeseQuantityTextField);
		cheeseShoppingForm.add(addCheeseButton);
		cheeseShoppingForm.add(removeCheeseButton);
		add(cheeseShoppingForm);
	}
	
	private void toggleComponentVisibility() {
		
//
//		//Append display:block or display:hidden to hide content
//		AttributeAppender showComponent = new AttributeAppender("class",
//				new Model<String>("unhide"), " ");
//		AttributeAppender hideComponent = new AttributeAppender("class",
//				new Model<String>("hide"), " ");
		
		if(hasBought) {
			cheeseQuantityTextField.setEnabled(false);
//			addCheeseButton.add(hideComponent);
			addCheeseButton.setVisible(false);
//			removeCheeseButton.add(showComponent);
			removeCheeseButton.setVisible(true);
		} else {
//			addCheeseButton.add(showComponent);
//			removeCheeseButton.add(hideComponent);
			cheeseQuantityTextField.setEnabled(true);
			addCheeseButton.setVisible(true);
			removeCheeseButton.setVisible(false);
		}
	}
	
	public void attachListener(CheesrCartActionListener cheesrCartOrderListener) {
		this.cheesrCartOrderListener=cheesrCartOrderListener;
	}

}
