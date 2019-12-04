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
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.delegate.DelegateBus;
import co.edu.icesi.delegate.DelegateRuta;
import co.edu.icesi.model.Tmio1Ruta;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RutaDelegateTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	private DelegateRuta rutaDelegate;

	final String URI_SERVER = "http://localhost:8080/api/";

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetBus() {
		assertNotNull(rutaDelegate);

		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setNumero("1");
		ruta1.setActiva("si");
		ruta1.setDescripcion("universitaria");
		ruta1.setDiaInicio(new BigDecimal(1));
		ruta1.setDiaFin(new BigDecimal(3));
		ruta1.setHoraInicio(new BigDecimal(1));
		ruta1.setHoraFin(new BigDecimal(1000));
		ruta1.setId(1);

		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setNumero("1");
		ruta2.setActiva("si");
		ruta2.setDescripcion("universitaria");
		ruta2.setDiaInicio(new BigDecimal(1));
		ruta2.setDiaFin(new BigDecimal(3));
		ruta2.setHoraInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal(1000));
		ruta2.setId(2);

		Tmio1Ruta[] buses = { ruta1, ruta2 };

		Mockito.when(restTemplate.getForObject(URI_SERVER + "rutas", Tmio1Ruta[].class)).thenReturn(buses);

		Iterable<Tmio1Ruta> aux = rutaDelegate.getTmioRutas();

		assertEquals(ruta1.getId(), aux.iterator().next().getId());
	}

	@Test
	public void testAgregarBus() {

		assertNotNull(rutaDelegate);
		
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setNumero("1");
		ruta1.setActiva("si");
		ruta1.setDescripcion("universitaria");
		ruta1.setDiaInicio(new BigDecimal(1));
		ruta1.setDiaFin(new BigDecimal(3));
		ruta1.setHoraInicio(new BigDecimal(1));
		ruta1.setHoraFin(new BigDecimal(1000));
		ruta1.setId(1);

		Mockito.when(restTemplate.getForObject(URI_SERVER + "rutas/" + ruta1.getId(), Tmio1Ruta.class)).thenReturn(ruta1);

		assertNotNull(ruta1);
		Tmio1Ruta b = rutaDelegate.getTmioRuta(ruta1.getId());

		assertEquals(ruta1.getId(), b.getId());
	}
}
