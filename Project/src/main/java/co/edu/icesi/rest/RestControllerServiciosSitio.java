package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.model.Tmio1ServiciosSitioPK;
import co.edu.icesi.services.ServicioSitioService;

@RestController
public class RestControllerServiciosSitio implements IRestControllerServiciosSitio{

	@Autowired
	private ServicioSitioService serviciosSitioService;

	@GetMapping("api/serviciosSitios")
	public Iterable<Tmio1ServiciosSitio> getServiciosSitios() {
		return serviciosSitioService.findAll();
	}

	@PostMapping("api/serviciosSitios")
	public Tmio1ServiciosSitio addServiciosSitios(Tmio1ServiciosSitio serviciosSitios) {
		serviciosSitioService.save(serviciosSitios);
		return serviciosSitios;
	}

	@GetMapping("api/serviciosSitios/{id}")
	public Tmio1ServiciosSitio getServiciosSitio(Tmio1ServiciosSitioPK id) {
		return serviciosSitioService.findById(id);
	}

	@DeleteMapping("api/serviciosSitios/{id}")
	public Tmio1ServiciosSitio delServiciosSitio(Tmio1ServiciosSitioPK id) {
		return serviciosSitioService.delete(serviciosSitioService.findById(id));
	}

	
	
	
	
}
