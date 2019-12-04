package co.edu.icesi.services;

import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.RutasDao;
import co.edu.icesi.dao.SitiosDao;
import co.edu.icesi.dao.SitiosRutasDao;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Sitio;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

@Service
public class SitioRutaService {

	@Autowired
	private SitiosRutasDao repository;
	
	@Transactional
	public Tmio1SitiosRuta save(Tmio1SitiosRuta tmio1SitioRuta) throws Exception{
		
		Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
		int x = tmio1SitioRuta.getIdruta();
		int y = tmio1SitioRuta.getIdsitio();
		
		pk.setIdRuta(x);
		pk.setIdSitio(y);
		
		tmio1SitioRuta.setId(pk);
		tmio1SitioRuta.setId_hash(pk.hashCode());
		
		repository.save(tmio1SitioRuta);
		return tmio1SitioRuta;
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
	
	@Transactional
	public Tmio1SitiosRuta delete(int sitioRuta) {
		
		
		Iterator<Tmio1SitiosRuta> list  = findAll().iterator();
		Tmio1SitiosRuta tsr = null;
		
        while (list.hasNext()){
        	Tmio1SitiosRuta aux = list.next();
        	if(aux.getId_hash()==sitioRuta) {
        		tsr = aux; 
        	}
        }
        
		repository.delete(tsr);
		return tsr;
	}

	
	
	
}
