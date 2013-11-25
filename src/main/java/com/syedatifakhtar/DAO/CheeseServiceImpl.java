package com.syedatifakhtar.DAO;

import java.util.List;

import com.syedatifakhtar.model.Cheese;

public class CheeseServiceImpl implements CheeseService{

	private CheeseDAO cheeseDAO;
	
	@Override
	public List<Cheese> findAll() {
		return cheeseDAO.findAll();
	}

	@Override
	public Cheese getCheese(long id) {
		return cheeseDAO.getCheese(id);
	}

	@Override
	public long saveCheese(Cheese cheese) {
		// TODO Auto-generated method stub
		return cheeseDAO.saveCheese(cheese);
	}

	@Override
	public void updateCheese(Cheese cheese) {
		cheeseDAO.updateCheese(cheese);
	}
	
	@Override
	public void deleteCheese(Cheese cheese) {
		cheeseDAO.deleteCheese(cheese);
		
	}
	
	public CheeseDAO getCheeseDAO() {
		return cheeseDAO;
	}

	public void setCheeseDAO(CheeseDAO cheeseDAO) {
		this.cheeseDAO = cheeseDAO;
	}
}
