package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.exceptions.ServicioNullException;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;
import co.edu.icesi.services.ServicioService;

@RestController
public class RestControllerServicio implements IRestControllerServicio{

	
	@Autowired
	private ServicioService servicioService;

	@GetMapping("api/servicios")
	public Iterable<Tmio1Servicio> getServicios() {
		return servicioService.findAll();
	}

	@PostMapping("api/servicios")
	public Tmio1Servicio addServicio(Tmio1Servicio servicio) {
		
		try {
			servicioService.save(servicio);
		} catch (BusNullException | ConductorNullException | RutaNullException | FechasNoConsistentesException
				| ServicioNullException e) {
			e.printStackTrace();
		}
		
		return servicio;
	}

	@DeleteMapping("api/servicios/{id}")
	public Tmio1Servicio delServicio(Tmio1ServicioPK id) {
		return servicioService.delete(servicioService.findById(id));
	}

	@GetMapping("api/servicios/{id}")
	public Tmio1Servicio getServicio(Tmio1ServicioPK id) {
		return servicioService.findById(id);
	}
}
