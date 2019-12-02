package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Sitio;

@Component
public class DelegateSitio implements IDelegateSitio {

	private RestTemplate restTemplate;
	public final static String SERVER = "http://localhost:8080/api/";

	public DelegateSitio() {
		restTemplate = new RestTemplate();
	}

	@Override
	public Iterable<Tmio1Sitio> getTmioSitios() {

		Tmio1Sitio[] sitios = restTemplate.getForObject(SERVER + "sitios/", Tmio1Sitio[].class);
		List<Tmio1Sitio> lista = null;

		try {
			lista = Arrays.asList(sitios);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Tmio1Sitio addTmioSitio(Tmio1Sitio newTmio1Sitio) throws Exception {
		return restTemplate.postForEntity(SERVER + "sitios",newTmio1Sitio, Tmio1Sitio.class).getBody();
	}

	@Override
	public void delTmioSitio(long id) {
		restTemplate.delete(SERVER + "sitios/" + id);
	}

	@Override
	public Tmio1Sitio getTmioSitio(long id) {
		return restTemplate.getForObject( SERVER + "sitios/" + id, Tmio1Sitio.class);
	}

}
