package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.model.Tmio1ServiciosSitioPK;

@Component
public class DelegateServiciosSitio implements IDelegateServiciosSitio {

	
	RestTemplate restTemplate;
	public final static String SERVER = "http://localhost:8080/api/";
	
	public DelegateServiciosSitio(DelegateServiciosSitio delegateServiciosSitio) {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public Iterable<Tmio1ServiciosSitio> getTmioServiciosSitios() {
		
		Tmio1ServiciosSitio[] serviciosSitio = restTemplate.getForObject(SERVER + "serviciosSitios", Tmio1ServiciosSitio[].class);
		List<Tmio1ServiciosSitio> at = null;
		 
		try {
			at = Arrays.asList(serviciosSitio);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Tmio1ServiciosSitio addTmioServiciosSitio(Tmio1ServiciosSitio newServiciosSitio) {
		return restTemplate.postForEntity( SERVER + "serviciosSitios", newServiciosSitio, Tmio1ServiciosSitio.class).getBody();
	}
	
	@Override
	public void delTmioServiciosSitio(Tmio1ServiciosSitio tmioServiciosSitio) {
		restTemplate.delete(SERVER + "serviciosSitios" + tmioServiciosSitio.getId()); 
	}
	
	@Override
	public Tmio1ServiciosSitio getTmioServicioSitio(Tmio1ServiciosSitioPK id) {
		return restTemplate.getForObject( SERVER + "serviciosSitios/" + id, Tmio1ServiciosSitio.class);
	}		
	
	
	
}
