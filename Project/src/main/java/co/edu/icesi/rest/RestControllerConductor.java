package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.FormatoIncorrectoException;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.services.ConductorService;

@RestController
public class RestControllerConductor implements IRestControllerConductor{

	@Autowired
	private ConductorService conductorService;
	
	
	@GetMapping("api/conductores")
	public Iterable<Tmio1Conductore> getConductores() {
		return conductorService.findAll();
	}

	@PostMapping("api/conductores/{id}")
	public Tmio1Conductore getConductor(String cedula) {
		return conductorService.findById(cedula);
	}

	@PostMapping("api/conductores")
	public Tmio1Conductore addConductor(@RequestBody Tmio1Conductore conductor)throws Exception {
		conductorService.save(conductor);
		return conductor;
	}

	@DeleteMapping("api/conductores/{id}")
	public Tmio1Conductore delConductor(String cedula) {
		return conductorService.deleted(conductorService.findById(cedula));
	}

	
	
}
