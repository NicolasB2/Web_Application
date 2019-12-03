package co.edu.icesi.delegate;


import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.model.Tmio1ServiciosSitioPK;

public interface IDelegateServiciosSitio {

	public Iterable<Tmio1ServiciosSitio> getTmioServiciosSitios();
	
	public Tmio1ServiciosSitio addTmioServiciosSitio(Tmio1ServiciosSitio newServiciosSitio);
	
	public void delTmioServiciosSitio(Tmio1ServiciosSitio tmioServiciosSitio);
	
	public Tmio1ServiciosSitio getTmioServicioSitio(Tmio1ServiciosSitioPK id);
}
