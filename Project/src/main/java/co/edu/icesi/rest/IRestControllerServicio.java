package co.edu.icesi.rest;

import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;

public interface IRestControllerServicio {

	public Iterable<Tmio1Servicio> getServicios();

	public Tmio1Servicio addServicio(Tmio1Servicio servicio);

	public Tmio1Servicio delServicio(Tmio1ServicioPK id);
	
	public Tmio1Servicio getServicio(Tmio1ServicioPK id);

}
