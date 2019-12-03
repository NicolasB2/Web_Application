package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Tmio1Sitio;
import co.edu.icesi.services.SitioService;

@RestController
public class RestControllerSitio implements IRestControllerSitio {

	
	@Autowired
	private SitioService sitioService;

	@GetMapping("api/sitios")
	public Iterable<Tmio1Sitio> getSitios() {
		return sitioService.findAll();
	}

	@PostMapping("api/sitios")
	public Tmio1Sitio addSitio(@RequestBody Tmio1Sitio sitio) throws Exception {
		sitioService.save(sitio);
		return sitio;
	}


	@GetMapping("api/sitios/{id}")
	public Tmio1Sitio getSitio(@PathVariable long id) {
		return sitioService.findById(id);
	}

	@DeleteMapping("api/sitios/{id}")
	public Tmio1Sitio delSitio(@PathVariable long id) {
		return sitioService.deleted(sitioService.findById(id));
	}
	
	
	
	
	
	
}
