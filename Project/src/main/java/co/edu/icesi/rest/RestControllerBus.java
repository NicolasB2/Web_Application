package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.services.BusService;

@RestController
public class RestControllerBus implements IRestControllerBus {
	
	@Autowired
	private BusService busService;

	@GetMapping("api/buses")
	public Iterable<Tmio1Bus> getBuses() {
		return busService.findAll();
	}

	@PostMapping("api/buses")
	public Tmio1Bus addBus(Tmio1Bus bus) {
		try {
			busService.save(bus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bus;
	}

	@DeleteMapping("api/buses/{id}")
	public Tmio1Bus delBus(Integer id) {
		return busService.deleted(busService.findById(id));
	}

	@GetMapping("api/buses/{id}")
	public Tmio1Bus getBus(Integer id) {
		return busService.findById(id);
	}

		
	
}
