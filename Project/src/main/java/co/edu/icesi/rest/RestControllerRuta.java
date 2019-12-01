package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.exceptions.FechaInvalidaException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.HoraInvalidaException;
import co.edu.icesi.exceptions.HoraNullException;
import co.edu.icesi.exceptions.HorasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.services.RutaService;

@RestController
public class RestControllerRuta implements IRestControllerRuta {

	
	@Autowired
	private RutaService rutaService; 
	
	
	@GetMapping("api/rutas")
	public Iterable<Tmio1Ruta> getRutas() {
		return rutaService.findAll();
	}

	@PostMapping("api/rutas")
	public Tmio1Ruta addRuta(@RequestBody Tmio1Ruta ruta) throws Exception {
		return rutaService.save(ruta);
	}

	@GetMapping("api/rutas/{id}")
	public Tmio1Ruta getRuta(@PathVariable int id) {
		return rutaService.findById(id);
	}
	
	@DeleteMapping("api/rutas/{id}")
	public Tmio1Ruta delRuta(@PathVariable int id) {
		return rutaService.deleted(rutaService.findById(id));
	}
	
}
