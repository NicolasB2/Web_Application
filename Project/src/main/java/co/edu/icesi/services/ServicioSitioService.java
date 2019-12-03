package co.edu.icesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.RutasDao;
import co.edu.icesi.dao.ServiciosSitioDao;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.model.Tmio1ServiciosSitioPK;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

@Service
public class ServicioSitioService {
	
	@Autowired
	private ServiciosSitioDao repository;
	
	public Tmio1ServiciosSitio save(Tmio1ServiciosSitio serviciosRuta) {
		repository.save(serviciosRuta);
		return serviciosRuta;
	}

	public void setRepository(ServiciosSitioDao repository) {
		this.repository = repository;
	}
	
	public Iterable<Tmio1ServiciosSitio> findAll(){
		return repository.findAll();
	}
	
	public Tmio1ServiciosSitio findById(Tmio1ServiciosSitioPK id) {
		return repository.findById(id); 
	}
	
	public Tmio1ServiciosSitio delete(Tmio1ServiciosSitio serviciosRuta) {
		repository.delete(serviciosRuta);
		return serviciosRuta;
	}
	
	

}
