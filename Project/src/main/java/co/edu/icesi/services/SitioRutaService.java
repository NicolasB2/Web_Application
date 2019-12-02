package co.edu.icesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.SitiosRutasDao;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

@Service
public class SitioRutaService {

	@Autowired
	private SitiosRutasDao repository;
	
	public Tmio1SitiosRuta save(Tmio1SitiosRuta sitiosRuta) throws Exception{
		repository.save(sitiosRuta);
		return sitiosRuta;
	}
	
	public void setRepository(SitiosRutasDao repository){
		this.repository = repository;
	}
	
	public Iterable<Tmio1SitiosRuta> findAll(){
		return repository.findAll();
	}
	
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id) {
		return repository.findById(id);
	}
	
	public Tmio1SitiosRuta delete(Tmio1SitiosRuta sitioRuta) {
		repository.delete(sitioRuta);
		return sitioRuta;
	}
	
}
