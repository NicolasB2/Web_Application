package co.edu.icesi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.services.BusService;

@RestController
public class RestControllerBus {
	
	private BusService service;

	@RequestMapping("/api/buses/consult")
	public Tmio1Bus finById(Tmio1Bus tmio1Bus){
		return service.findById(tmio1Bus.getId()) ;
	}
	
	
}
