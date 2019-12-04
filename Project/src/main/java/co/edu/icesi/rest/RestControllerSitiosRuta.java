package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.Tmio1Sitio;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;
import co.edu.icesi.services.SitioRutaService;

@RestController
public class RestControllerSitiosRuta implements IRestControllerSitiosRuta {

	@Autowired
	private SitioRutaService sitioRutaService;

	@PostMapping("api/sitiosRutas")
	public Tmio1SitiosRuta addSitiosRuta(@RequestBody Tmio1SitiosRuta sitiosRuta) throws Exception {
		sitioRutaService.save(sitiosRuta);
		return sitiosRuta;
	}

	@GetMapping("api/sitiosRutas")
	public Iterable<Tmio1SitiosRuta> getSitiosRutas() {
		return sitioRutaService.findAll();
	}
	
	@DeleteMapping("api/sitiosRutas/{id_hash}")
	public Tmio1SitiosRuta delSitio(@PathVariable ("id_hash")  int id_hash) {
		return sitioRutaService.delete(id_hash);
	}

	@Override
	public Tmio1SitiosRuta getSitiosRuta(Tmio1SitiosRutaPK id) {
		return sitioRutaService.findById(id);
	}
	
}
