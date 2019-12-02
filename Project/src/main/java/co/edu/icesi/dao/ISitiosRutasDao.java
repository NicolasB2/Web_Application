package co.edu.icesi.dao;

import java.util.List;

import co.edu.icesi.model.Tmio1Sitio;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

public interface ISitiosRutasDao {

	
	public void save(Tmio1SitiosRuta entity);
	public void update(Tmio1SitiosRuta entity);
	public void delete(Tmio1SitiosRuta entity);
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id);
	public List<Tmio1SitiosRuta> findAll();
	
	
}
