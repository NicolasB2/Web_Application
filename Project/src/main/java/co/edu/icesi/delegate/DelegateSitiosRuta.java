package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

@Component
public class DelegateSitiosRuta implements IDelegateSitioRuta {

	private RestTemplate restTemplate;
	public final static String SERVER = "http://localhost:8080/api/";
	
	public DelegateSitiosRuta() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public Iterable<Tmio1SitiosRuta> getTmioSitiosRutas() {
		Tmio1SitiosRuta[] sitioRuta = restTemplate.getForObject(SERVER + "sitiosRutas", Tmio1SitiosRuta[].class);
		
		List<Tmio1SitiosRuta> list = null;
		
		try {
			list = Arrays.asList(sitioRuta);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Tmio1SitiosRuta addTmioSitioRuta(Tmio1SitiosRuta newTmio1SitiosRuta) throws Exception {
		Tmio1SitiosRuta sitioRuta = restTemplate.postForEntity(SERVER + "sitiosRutas", newTmio1SitiosRuta, Tmio1SitiosRuta.class).getBody();
		return sitioRuta;
	}

	@Override
	public void delTmioSitioRuta(Tmio1SitiosRuta tmio1SitiosRuta) {
		restTemplate.delete(SERVER + "sitiosRutas/" + tmio1SitiosRuta.getId());
	}

	@Override
	public Tmio1SitiosRuta getTmioSitiosRuta(Tmio1SitiosRutaPK id) {
		Tmio1SitiosRuta sitioRuta = restTemplate.getForObject(SERVER + "sitiosRutas/" + id , Tmio1SitiosRuta.class);
		return sitioRuta;
	}

	
	
	
}
