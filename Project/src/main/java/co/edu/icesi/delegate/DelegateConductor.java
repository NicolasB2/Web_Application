package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Tmio1Conductore;

@Component
public class DelegateConductor implements IDelegateConductor {

	
	RestTemplate restTemplate;
	public final static String SERVER = "http://localhost:8080/api/";	

	public DelegateConductor() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public Iterable<Tmio1Conductore> getTmioConductores() {
		
		Tmio1Conductore[] conductores = restTemplate.getForObject(SERVER + "conductores", Tmio1Conductore[].class);
		List<Tmio1Conductore> at = null;
		
		try {
			at = Arrays.asList(conductores);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Tmio1Conductore addTmioConductor(Tmio1Conductore newTmioConductor) throws Exception{
		return restTemplate.postForEntity(SERVER + "conductores", newTmioConductor, Tmio1Conductore.class).getBody();
	}

	@Override
	public Tmio1Conductore getTmioCondutor(String id) {
		return restTemplate.getForObject(SERVER + "conductores/" + id, Tmio1Conductore.class);
	}
	
	@Override
	public void delTmioConductor(Tmio1Conductore newTmioConductor) {
		restTemplate.delete(SERVER + "conductores" + newTmioConductor.getCedula());
	}

	

}
