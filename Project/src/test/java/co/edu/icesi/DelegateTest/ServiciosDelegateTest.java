package co.edu.icesi.DelegateTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import org.springframework.web.client.RestTemplate;
import co.edu.icesi.delegate.DelegateServicio;
import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ServiciosDelegateTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	private DelegateServicio servicioDelegate;

	final String URI_SERVER = "http://localhost:8080/api/";

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetServicio() {
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Auidi");
		bus.setPlaca("KJB864");
		bus.setModelo(new BigDecimal("2019"));
		bus.setId(1);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2015,04,12));
		conductor.setFechaNacimiento(LocalDate.of(1999,03,29));

		conductor.setCedula("119876542782");
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz");

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 70 until street 92");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		ruta.setId(1);

		Tmio1Servicio srv = new Tmio1Servicio();

		srv.setTmio1Bus(bus);
		srv.setTmio1Conductore(conductor);
		srv.setTmio1Ruta(ruta);

		srv.setTmioFechaInicio(LocalDate.of(2016,03,12));
		srv.setTmioFechaFin(LocalDate.of(2019,03,21));

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(srv.getTmio1Conductore().getCedula());
		srvPK.setIdBus(srv.getTmio1Bus().getId());
		srvPK.setIdRuta(srv.getTmio1Ruta().getId());
		srvPK.setFechaInicio(srv.getTmioFechaInicio());
		srvPK.setFechaFin(srv.getTmioFechaFin());

		srv.setId(srvPK);
		srv.setId_hash(srvPK.getHashCode());
		
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(10));
		bus2.setTipo(BusType.T);
		bus2.setMarca("mazda");
		bus2.setPlaca("YGV875");
		bus2.setModelo(new BigDecimal("2019"));
		bus2.setId(2);


		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setNumero("1");
		ruta2.setActiva("yes");
		ruta2.setDescripcion("Street 50 until street 12");
		ruta2.setDiaInicio(new BigDecimal(4));
		ruta2.setDiaFin(new BigDecimal(5));
		ruta2.setHoraInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal(2000));
		ruta2.setId(2);

		Tmio1Servicio srv2 = new Tmio1Servicio();

		srv2.setTmio1Bus(bus2);
		srv2.setTmio1Conductore(conductor);
		srv2.setTmio1Ruta(ruta2);

		srv2.setTmioFechaInicio(LocalDate.of(2016,03,12));
		srv2.setTmioFechaFin(LocalDate.of(2019,03,21));

		Tmio1ServicioPK srvPK2 = new Tmio1ServicioPK();
		srvPK2.setCedulaConductor(srv2.getTmio1Conductore().getCedula());
		srvPK2.setIdBus(srv2.getTmio1Bus().getId());
		srvPK2.setIdRuta(srv2.getTmio1Ruta().getId());
		srvPK2.setFechaInicio(srv2.getTmioFechaInicio());
		srvPK2.setFechaFin(srv2.getTmioFechaFin());

		srv2.setId(srvPK2);
		srv2.setId_hash(srvPK2.getHashCode());
		
		assertNotNull(servicioDelegate);

		Tmio1Servicio[] servicios = { srv, srv2 };

		Mockito.when(restTemplate.getForObject(URI_SERVER + "servicios", Tmio1Servicio[].class)).thenReturn(servicios);

		Iterable<Tmio1Servicio> aux = servicioDelegate.getTmioServicios();

		assertEquals(srv.getId(), aux.iterator().next().getId());
	}

	@Test
	public void AgregarConductorTest() {

		assertNotNull(servicioDelegate);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Auidi");
		bus.setPlaca("KJB864");
		bus.setModelo(new BigDecimal("2019"));
		bus.setId(1);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Tmio1Conductore conductor = new Tmio1Conductore();
		
		conductor.setFechaContratacion(LocalDate.of(2015,04,12));
		conductor.setFechaNacimiento(LocalDate.of(1999,03,29));

		conductor.setCedula("119876542782");
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz");

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 70 until street 92");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		ruta.setId(1);

		Tmio1Servicio srv = new Tmio1Servicio();

		srv.setTmio1Bus(bus);
		srv.setTmio1Conductore(conductor);
		srv.setTmio1Ruta(ruta);

		srv.setTmioFechaInicio(LocalDate.of(2016,03,12));
		srv.setTmioFechaFin(LocalDate.of(2019,03,21));

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(srv.getTmio1Conductore().getCedula());
		srvPK.setIdBus(srv.getTmio1Bus().getId());
		srvPK.setIdRuta(srv.getTmio1Ruta().getId());
		srvPK.setFechaInicio(srv.getTmioFechaInicio());
		srvPK.setFechaFin(srv.getTmioFechaFin());

		srv.setId(srvPK);
		srv.setId_hash(srvPK.getHashCode());

		Mockito.when(restTemplate.getForObject(URI_SERVER + "servicios/" + srv.getId(), Tmio1Servicio.class)).thenReturn(srv);

		assertNotNull(srv);
		Tmio1Servicio b = servicioDelegate.getTmioServicio(srv.getId());

		assertEquals(srv.getId(), b.getId());

	}
}
