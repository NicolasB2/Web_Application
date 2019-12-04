package co.edu.icesi.rest;

import org.springframework.web.bind.annotation.PathVariable;

import co.edu.icesi.model.Tmio1Sitio;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

public interface IRestControllerSitiosRuta {

	
	public Tmio1SitiosRuta addSitiosRuta(Tmio1SitiosRuta sitiosRuta) throws Exception;
	
	public Iterable<Tmio1SitiosRuta> getSitiosRutas();
	
	public Tmio1SitiosRuta getSitiosRuta(Tmio1SitiosRutaPK id);
	public Tmio1SitiosRuta delSitio(int id);
	
}
