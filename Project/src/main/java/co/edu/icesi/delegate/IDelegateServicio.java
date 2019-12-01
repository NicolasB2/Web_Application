package co.edu.icesi.delegate;

import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;

public interface IDelegateServicio {

	
	public Iterable<Tmio1Servicio> getTmioServicios();

	public Tmio1Servicio addTmioServicio(Tmio1Servicio newTmioServicio) throws Exception;

	public void delTmioServicio(Tmio1Servicio newTmioServicio);

	public Tmio1Servicio getTmioServicio(Tmio1ServicioPK id);
	
	
}
