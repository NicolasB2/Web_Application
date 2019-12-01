package co.edu.icesi.rest;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Ruta;

public interface IRestControllerRuta {

	public Iterable<Tmio1Ruta> getRutas();

	public Tmio1Ruta addRuta(Tmio1Ruta ruta) throws Exception;

	public Tmio1Ruta delRuta(Integer id);
	
	public Tmio1Ruta getRuta(Integer id);

	
	
}
