package co.edu.icesi.DelegateTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import co.edu.icesi.delegate.DelegateConductor;
import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ConductorDelegateTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	private DelegateConductor conductorDelegate;

	final String URI_SERVER = "http://localhost:8080/api/";

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetConductor() {
		assertNotNull(conductorDelegate);

		Tmio1Conductore conductor1 = new Tmio1Conductore();
		conductor1.setFechaContratacion(LocalDate.of(2010, 12, 31));
		conductor1.setFechaNacimiento(LocalDate.of(1990,12,31));
		conductor1.setCedula("1234");
		conductor1.setNombre("Juan");
		conductor1.setApellidos("IG");

		Tmio1Conductore conductor2 = new Tmio1Conductore();
		conductor2.setFechaContratacion(LocalDate.of(2010, 12, 31));
		conductor2.setFechaNacimiento(LocalDate.of(1990,12,31));
		conductor2.setCedula("5678");
		conductor2.setNombre("Juan");
		conductor2.setApellidos("IG");

		Tmio1Conductore[] conductures = { conductor1, conductor2 };

		Mockito.when(restTemplate.getForObject(URI_SERVER + "conductores", Tmio1Conductore[].class)).thenReturn(conductures);

		Iterable<Tmio1Conductore> aux = conductorDelegate.getTmioConductores();

		assertEquals(conductor1.getCedula(), aux.iterator().next().getCedula());
	}

	@Test
	public void AgregarConductorTest() {

		assertNotNull(conductorDelegate);
		
		Tmio1Conductore conductor1 = new Tmio1Conductore();
		conductor1.setFechaContratacion(LocalDate.of(2010, 12, 31));
		conductor1.setFechaNacimiento(LocalDate.of(1990,12,31));
		conductor1.setCedula("1234");
		conductor1.setNombre("Juan");
		conductor1.setApellidos("IG");

		Mockito.when(restTemplate.getForObject(URI_SERVER + "conductores/" + conductor1.getCedula(), Tmio1Conductore.class)).thenReturn(conductor1);

		assertNotNull(conductor1);
		Tmio1Conductore b = conductorDelegate.getTmioCondutor(conductor1.getCedula());

		assertEquals(conductor1.getCedula(), b.getCedula());

	}
}
