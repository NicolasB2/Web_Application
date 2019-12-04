package co.edu.icesi.delegate;

import co.edu.icesi.model.Tmio1Sitio;

public interface IDelegateSitio {

	public Iterable<Tmio1Sitio> getTmioSitios();

	public Tmio1Sitio addTmioSitio(Tmio1Sitio newTmio1Sitio) throws Exception;

	public void delTmioSitio(long id);

	public Tmio1Sitio getTmioSitio(long id);
	
	public void update(Tmio1Sitio tmio1Sitio);
	
	public void eliminarS(Long id);
	
}
