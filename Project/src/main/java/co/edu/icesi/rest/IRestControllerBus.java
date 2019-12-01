package co.edu.icesi.rest;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;

public interface IRestControllerBus {
	
	public Tmio1Bus addBus(Tmio1Bus bus) throws Exception;

	public Iterable<Tmio1Bus> getBuses();

	public Tmio1Bus getBus(int id);
	public Tmio1Bus delBus(int id);
	
	public BusType[] getTipoBus();
	/*public Iterable<Tmio1Bus> findAllByPlaca(String placa);*/
	
	

}
