package co.edu.icesi.delegate;

import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

public interface IDelegateSitioRuta {

	public Iterable<Tmio1SitiosRuta> getTmioSitiosRutas();
	
	public Tmio1SitiosRuta addTmioSitioRuta(Tmio1SitiosRuta newTmio1SitiosRuta) throws Exception;
	
	public void delTmioSitioRuta(Tmio1SitiosRuta tmio1SitiosRuta );
	
	public Tmio1SitiosRuta getTmioSitiosRuta (Tmio1SitiosRutaPK id); 
	
}
