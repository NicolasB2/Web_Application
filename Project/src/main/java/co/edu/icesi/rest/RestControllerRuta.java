package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.icesi.exceptions.FechaInvalidaException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.HoraInvalidaException;
import co.edu.icesi.exceptions.HoraNullException;
import co.edu.icesi.exceptions.HorasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.services.RutaService;

public class RestControllerRuta implements IRestControllerRuta {

	
	@Autowired
	private RutaService rutaService; 
	
	
	@GetMapping("api/rutas")
	public Iterable<Tmio1Ruta> getRutas() {
		return rutaService.findAll();
	}

	@PostMapping("api/buses")
	public Tmio1Ruta addRuta(Tmio1Ruta ruta) {
		
		try {
			rutaService.save(ruta);
		} catch (RutaNullException | FechasNoConsistentesException | HorasNoConsistentesException | FechaNullException
				| HoraNullException | FechaInvalidaException | HoraInvalidaException e) {
			e.printStackTrace();
		}
		return ruta;
	}

	@DeleteMapping("api/rutas/{id}")
	public Tmio1Ruta delRuta(Integer id) {
		return rutaService.deleted(rutaService.findById(id));
	}

	@GetMapping("api/buses/{id}")
	public Tmio1Ruta getRuta(Integer id) {
		return rutaService.findById(id);
	}

	
	
	
	
	
	
}
