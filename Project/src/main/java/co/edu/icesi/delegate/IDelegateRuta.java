package co.edu.icesi.delegate;

import co.edu.icesi.model.Tmio1Ruta;

public interface IDelegateRuta {

	public Iterable<Tmio1Ruta> getTmioRutas();

	public Tmio1Ruta addTmioRuta(Tmio1Ruta newTmioRuta) throws Exception;

	public void delTmioRuta(Tmio1Ruta tmioRuta);

	public Tmio1Ruta getTmioRuta(int id);
	
	
}
