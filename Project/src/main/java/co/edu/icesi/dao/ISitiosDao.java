package co.edu.icesi.dao;

import java.util.List;

import co.edu.icesi.model.Tmio1Sitio;

public interface ISitiosDao {
	
	
	public void save(Tmio1Sitio entity);
	public void update(Tmio1Sitio entity);
	public void delete(Tmio1Sitio entity);
	
	public Tmio1Sitio findById(long id);
	public List<Tmio1Sitio> findAll();
	
	

}
