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

import co.edu.icesi.delegate.DelegateRuta;
import co.edu.icesi.delegate.DelegateSitio;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Sitio;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SitioDelegateTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	private DelegateSitio sitioDelegate;

	final String URI_SERVER = "http://localhost:8080/api/";

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetSitio() {
		assertNotNull(sitioDelegate);

		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setNombre("San Antonio");
		sitio.setDescripcion("Barrio antiguo catalogado como patrimonio");
		sitio.setId(1);
		
		Tmio1Sitio sitio1 = new Tmio1Sitio();
		sitio1.setNombre("Pampalinda");
		sitio1.setDescripcion("Barrio nuevo");
		sitio1.setId(2);

		Tmio1Sitio[] sitios = { sitio, sitio1 };

		Mockito.when(restTemplate.getForObject(URI_SERVER + "sitios/", Tmio1Sitio[].class)).thenReturn(sitios);

		Iterable<Tmio1Sitio> aux = sitioDelegate.getTmioSitios();

		assertEquals(sitio.getId(), aux.iterator().next().getId());
	}

	@Test
	public void testAgregarSitio() {

		assertNotNull(sitioDelegate);
		
		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setNombre("Plaza central");
		sitio.setDescripcion("Ubicada en el centro de la ciudad");
		sitio.setId(3);
		
		
		Mockito.when(restTemplate.getForObject(URI_SERVER + "sitios/" + sitio.getId(), Tmio1Sitio.class)).thenReturn(sitio);

		assertNotNull(sitio);
		Tmio1Sitio b = sitioDelegate.getTmioSitio(sitio.getId());

		assertEquals(sitio.getId(), b.getId());
	}
}
