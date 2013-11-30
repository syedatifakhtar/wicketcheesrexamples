package com.syedatifakhtar.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.syedatifakhtar.DAO.CheeseOrderService;
import com.syedatifakhtar.model.Cheese;

public class ShoppingPanel {

	@SpringBean(name="cheeseService")
	private CheeseOrderService cheeseOrderService;
	
	private Label cheeseName;
	private Label cheeseDescription;
	private Button addCheeseButton;
	private Button removeCheeseButton;
	private TextField<Integer> cheeseQuantityTextField;
	private Integer cheeseQuantity;
	private Form<Void> cheeseActionForm;
	private Cheese cheese;

}
