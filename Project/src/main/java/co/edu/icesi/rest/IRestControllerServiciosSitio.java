package co.edu.icesi.rest;


import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.model.Tmio1ServiciosSitioPK;

public interface IRestControllerServiciosSitio {

	public Iterable<Tmio1ServiciosSitio> getServiciosSitios();

	public Tmio1ServiciosSitio addServiciosSitios(Tmio1ServiciosSitio serviciosSitios);

	public Tmio1ServiciosSitio delServiciosSitio(Tmio1ServiciosSitioPK id);
	
	public Tmio1ServiciosSitio getServiciosSitio(Tmio1ServiciosSitioPK id);
	
	
}
