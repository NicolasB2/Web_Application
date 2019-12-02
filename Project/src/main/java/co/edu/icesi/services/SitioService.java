package co.edu.icesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.BusesDao;
import co.edu.icesi.dao.SitiosDao;
import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Sitio;

@Service
public class SitioService {
	
	@Autowired
	private SitiosDao repository;
	
	public Tmio1Sitio save(Tmio1Sitio sitio) {
		repository.save(sitio);
		return sitio;
	}
	
	public void setRepository(SitiosDao repository) {
		this.repository = repository;
	}
	
	public Iterable<Tmio1Sitio> findAll(){
		return repository.findAll();
	}

	public Tmio1Sitio findById(long id) {
		return repository.findById(id);
	}

	public Tmio1Sitio deleted(Tmio1Sitio sitio) {
		repository.delete(sitio);
		return sitio;
	}
	
	

}
