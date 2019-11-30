package co.edu.icesi.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.FormatoIncorrectoException;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.services.ConductorService;

@RestController
public class RestControllerConductor implements IRestControllerConductor{

	
	private ConductorService conductorService;
	
	
	@GetMapping("/api/conductores")
	public Iterable<Tmio1Conductore> getConductores() {
		return conductorService.findAll();
	}

	@PostMapping("/api/conductores/{id}")
	public Tmio1Conductore getConductor(String cedula) {
		return conductorService.findById(cedula);
	}

	@PostMapping("/api/conductores")
	public Tmio1Conductore addConductor(Tmio1Conductore conductor) {
		
		try {
			conductorService.save(conductor);
		} catch (ConductorNullException | FechasNoConsistentesException | FechaNullException
				| FormatoIncorrectoException e) {
			e.printStackTrace();
		}
		
		return conductor;
	}

	@GetMapping("api/conductores/{id}")
	public Tmio1Conductore delConductor(String cedula) {
		return conductorService.deleted(conductorService.findById(cedula));
	}

	
	
}
