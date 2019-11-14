package co.edu.icesi.post;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Tmio1Bus;

@RestController
public class controllerPostBus {

	@RequestMapping("/Buses")
	public Iterable<Tmio1Bus> getBuss(){
		return null ;
	}
	
	
}
