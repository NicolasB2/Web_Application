package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.rest.TransactionBody;

@Component
public class DelegateBus implements IDelegateBus {

	private RestTemplate restTemplate;
	public final static String SERVER = "http://localhost:8080/api/";	
	
	
	public DelegateBus() {
		restTemplate = new RestTemplate();
	}
	

	@Override
	public void addTmioBus(Tmio1Bus newTmioBus) throws Exception {
		restTemplate.postForEntity(SERVER + "buses",newTmioBus, Tmio1Bus.class);
	}
	
	@Override
	public Tmio1Bus getTmioBus(int id) {
		return restTemplate.getForObject( SERVER + "buses" + id, Tmio1Bus.class);
	}
	
	@Override
	public void delTmioBus(int id) {
		restTemplate.delete(SERVER + "buses/" + id);
	}
 
	@Override
	public Iterable<Tmio1Bus> getTmioBuses() {
		
		Tmio1Bus[] buses = restTemplate.getForObject(SERVER + "buses/", Tmio1Bus[].class);

		List<Tmio1Bus> at = null;
		try {
			at = Arrays.asList(buses);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public BusType[] getType() {
		return restTemplate.getForEntity(SERVER + "buses/types", BusType[].class).getBody();
	}

	
	
	
}
