package co.edu.icesi.delegate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.rest.TransactionBody;

@Component
public class DelegateBus {

	
	private final static String REST_URI = "localhost:8080/";
	private RestTemplate restTemplate = null;
	
	public Tmio1Bus findById (Tmio1Bus tmio1Bus) {
		
		ResponseEntity response = null;
		TransactionBody transaction;
		/*HttpEntity request = new HttpEntity(transaction);
		
		
		try {
			response = restTemplate.exchange(REST_URI+ "api/buses/consult", HttpMethod.GET, request) {
			});
			
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		return null;
		
	}
}
