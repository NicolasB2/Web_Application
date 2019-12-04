package co.edu.icesi.DelegateTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.delegate.DelegateBus;
import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BusesDelegateTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	private DelegateBus busDelegate;

	final String URI_SERVER = "http://localhost:8080/api/";

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetBus() {
		assertNotNull(busDelegate);

		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(50));
		bus1.setTipo(BusType.T);
		bus1.setMarca("chevrolet");
		bus1.setPlaca("ABC123");
		bus1.setModelo(new BigDecimal("2018"));
		bus1.setId(1);

		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(50));
		bus2.setTipo(BusType.T);
		bus2.setMarca("mazda");
		bus2.setPlaca("ABC456");
		bus2.setModelo(new BigDecimal("2019"));
		bus2.setId(2);

		Tmio1Bus[] buses = { bus1, bus2 };

		Mockito.when(restTemplate.getForObject(URI_SERVER + "buses/", Tmio1Bus[].class)).thenReturn(buses);

		Iterable<Tmio1Bus> aux = busDelegate.getTmioBuses();

		assertEquals(bus1.getId(), aux.iterator().next().getId());
	}

	@Test
	public void testAgregarBus() {

		assertNotNull(busDelegate);
		
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(50));
		bus1.setTipo(BusType.T);
		bus1.setMarca("chevrolet");
		bus1.setPlaca("ABC123");
		bus1.setModelo(new BigDecimal("2018"));
		bus1.setId(1);

		Mockito.when(restTemplate.getForObject(URI_SERVER + "buses/" + bus1.getId(), Tmio1Bus.class)).thenReturn(bus1);

		assertNotNull(bus1);
		Tmio1Bus b = busDelegate.getTmioBus(bus1.getId());

		assertEquals(bus1.getId(), b.getId());
	}
}
