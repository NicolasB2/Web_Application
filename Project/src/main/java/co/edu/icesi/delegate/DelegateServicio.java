package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;

@Component
public class DelegateServicio implements IDelegateServicio {

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/";	
	
	public DelegateServicio() {
		restTemplate = new RestTemplate();
	}
	
	
	@Override
	public Iterable<Tmio1Servicio> getTmioServicios() {
		
		Tmio1Servicio[] servicio = restTemplate.getForObject(SERVER + "servicios", Tmio1Servicio[].class);

		List<Tmio1Servicio> at = null;
		try {
			at = Arrays.asList(servicio);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	@Override
	public Tmio1Servicio getTmioServicio(Tmio1ServicioPK id) {
		Tmio1Servicio servicio = restTemplate.getForObject(SERVER + "servicios/" + id , Tmio1Servicio.class);
		return servicio;
	}

	@Override
	public Tmio1Servicio addTmioServicio(Tmio1Servicio newTmioServicio) throws Exception {
		Tmio1Servicio servicio = restTemplate.postForEntity(SERVER + "servicios", newTmioServicio , Tmio1Servicio.class).getBody();
		return servicio;
	}

	@Override
	public void delTmioServicio(Tmio1Servicio newTmioServicio) {
		restTemplate.delete(SERVER + "servicios/" + newTmioServicio.getId());
	}


	
	
	
}
