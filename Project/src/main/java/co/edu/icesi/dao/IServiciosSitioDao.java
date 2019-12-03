package co.edu.icesi.dao;

import java.util.List;

import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.model.Tmio1ServiciosSitioPK;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

public interface IServiciosSitioDao {
	
	public void save(Tmio1ServiciosSitio entity);
	public void update(Tmio1ServiciosSitio entity);
	public void delete(Tmio1ServiciosSitio entity);
	public Tmio1ServiciosSitio findById(Tmio1ServiciosSitioPK id);
	public List<Tmio1ServiciosSitio> findAll();
	
}
