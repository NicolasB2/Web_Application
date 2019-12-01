package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Ruta;

@Component
public class DelegateRuta implements IDelegateRuta {

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/";		
	
	public DelegateRuta() {
		restTemplate = new RestTemplate(); 
	}
	
	
	@Override
	public Iterable<Tmio1Ruta> getTmioRutas() {
		
		Tmio1Ruta[] rutas = restTemplate.getForObject(SERVER + "rutas", Tmio1Ruta[].class);
		
		List<Tmio1Ruta> at = null;
		try {
			at = Arrays.asList(rutas);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Tmio1Ruta getTmioRuta(int id){
		Tmio1Ruta ruta = restTemplate.getForObject( SERVER + "ruta/" + id, Tmio1Ruta.class);
		return ruta;
	}
	
	@Override
	public Tmio1Ruta addTmioRuta(Tmio1Ruta newTmioRuta) throws Exception {
		Tmio1Ruta ruta =  restTemplate.postForEntity( SERVER + "ruta/", newTmioRuta, Tmio1Ruta.class).getBody();
		return ruta;
	}

	@Override
	public void delTmioRuta(Tmio1Ruta tmioRuta) {
		restTemplate.delete(SERVER + "rutas/" + tmioRuta.getId());
	}



	
	
}
