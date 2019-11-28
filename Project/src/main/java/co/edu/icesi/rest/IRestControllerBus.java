package co.edu.icesi.rest;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.model.Tmio1Bus;

public interface IRestControllerBus {
	

	public Iterable<Tmio1Bus> getBuses();

	public Tmio1Bus addBus(Tmio1Bus bus);

	public Tmio1Bus delBus(Integer id);
	
	public Tmio1Bus getBus(Integer id);

}
