package com.syedatifakhtar.DAO;

import java.io.Serializable;

import com.syedatifakhtar.model.Cheese;

public interface CheesrCartActionListener extends Serializable{
	
	public void addCheese(Cheese cheese,Integer quantity);
	public void removeCheese(Cheese cheese);

}
