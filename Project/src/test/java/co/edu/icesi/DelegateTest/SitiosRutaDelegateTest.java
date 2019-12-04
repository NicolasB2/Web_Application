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

import co.edu.icesi.delegate.DelegateSitiosRuta;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Sitio;
import co.edu.icesi.model.Tmio1SitiosRuta;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SitiosRutaDelegateTest {

	
	@Mock
	private RestTemplate restTemplate;
	
	
	@InjectMocks
	@Autowired
	private DelegateSitiosRuta sitioDelegate;

	final String URI_SERVER = "http://localhost:8080/api/";

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAgregarSitiosRuta() {
		
		assertNotNull(sitioDelegate);
		

		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setNombre("San Antonio");
		sitio.setDescripcion("Barrio antiguo catalogado como patrimonio");
		sitio.setId(1);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("si");
		ruta.setDescripcion("universitaria");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(1000));
		ruta.setId(1);
		
		
		Tmio1SitiosRutaPK pkSitiosRuta = new Tmio1SitiosRutaPK();
		pkSitiosRuta.setIdRuta(ruta.getId());
		pkSitiosRuta.setIdSitio((int) sitio.getId());
		
		Tmio1SitiosRuta sitioRuta = new Tmio1SitiosRuta();
		sitioRuta.setId(pkSitiosRuta);
		sitioRuta.setTmio1Ruta1(ruta);
		sitioRuta.setTmio1Sitio1(sitio);

		Mockito.when(restTemplate.getForObject(URI_SERVER + "sitiosRutas/" + sitioRuta.getId(), Tmio1SitiosRuta.class)).thenReturn(sitioRuta);

		assertNotNull(sitioRuta);
		Tmio1SitiosRuta b = sitioDelegate.getTmioSitiosRuta(sitioRuta.getId());

		assertEquals(sitioRuta.getId(), b.getId());

		
		
	}
	
	
	
	
}
