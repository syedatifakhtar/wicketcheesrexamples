package com.syedatifakhtar.DAO;

import java.util.List;

import com.syedatifakhtar.model.Cheese;

public interface CheeseDAO {
	
	public List<Cheese> findAll();
}
