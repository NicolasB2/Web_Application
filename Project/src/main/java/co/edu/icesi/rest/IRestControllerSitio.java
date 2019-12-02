package co.edu.icesi.rest;

import co.edu.icesi.model.Tmio1Sitio;

public interface IRestControllerSitio {

	public Tmio1Sitio addSitio(Tmio1Sitio sitio) throws Exception;
	
	public Iterable<Tmio1Sitio> getSitios();
	
	public Tmio1Sitio getSitio(long id);
	public Tmio1Sitio delSitio(long id);

}
