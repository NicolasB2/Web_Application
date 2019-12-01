package co.edu.icesi.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.Application;
import co.edu.icesi.dao.BusesDao;
import co.edu.icesi.dao.ConductoresDao;
import co.edu.icesi.dao.RutasDao;
import co.edu.icesi.dao.ServiciosDao;
import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Rollback(true)
public class QueryTest {

	@Autowired
	private ServiciosDao servicioDao;

	@Autowired
	private BusesDao busesDao;

	@Autowired
	private ConductoresDao conductoresDao;

	@Autowired
	private RutasDao rutasDao;

	@Before
	public void setUp() throws ParseException {

		// Buses
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Auidi");
		bus.setPlaca("ABC123");
		bus.setModelo(new BigDecimal("2019"));
		busesDao.save(bus);

		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(10));
		bus2.setTipo(BusType.A);
		bus2.setMarca("Auidi");
		bus2.setPlaca("XYZ098");
		bus2.setModelo(new BigDecimal("2010"));
		busesDao.save(bus2);

		// Rutas
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 70 until street 92");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		rutasDao.save(ruta);

		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setNumero("2");
		ruta2.setActiva("yes");
		ruta2.setDescripcion("Street 50 until street 2");
		ruta2.setDiaInicio(new BigDecimal(2));
		ruta2.setDiaFin(new BigDecimal(3));
		ruta2.setHoraInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal(2000));
		rutasDao.save(ruta2);

		// Conductores
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2015, 04, 12));
		conductor.setFechaNacimiento(LocalDate.of(1996, 03, 29));

		conductor.setCedula("0000000000");
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz");
		conductoresDao.save(conductor);

		Tmio1Conductore conductor2 = new Tmio1Conductore();

		conductor2.setFechaContratacion(LocalDate.of(2010, 07, 12));
		conductor2.setFechaNacimiento(LocalDate.of(1999, 03, 29));

		conductor2.setCedula("1111111111");
		conductor2.setNombre("Daniela");
		conductor2.setApellidos("Llano");
		conductoresDao.save(conductor2);

		// Servicios

		Tmio1Servicio srv = new Tmio1Servicio();

		srv.setTmio1Bus(bus);
		srv.setTmio1Conductore(conductor);
		srv.setTmio1Ruta(ruta);

		srv.setTmioFechaInicio(LocalDate.of(2016, 03, 5));
		srv.setTmioFechaFin(LocalDate.of(2019, 03, 6));

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(srv.getTmio1Conductore().getCedula());
		srvPK.setIdBus(srv.getTmio1Bus().getId());
		srvPK.setIdRuta(srv.getTmio1Ruta().getId());
		srvPK.setFechaInicio(srv.getTmioFechaInicio());
		srvPK.setFechaFin(srv.getTmioFechaFin());

		srv.setId(srvPK);
		srv.setId_hash(srvPK.getHashCode());

		servicioDao.save(srv);

		for (int i = 0; i < 10; i++) {

			Tmio1Servicio srv3 = new Tmio1Servicio();

			srv3.setTmio1Bus(bus2);
			srv3.setTmio1Conductore(conductor2);
			srv3.setTmio1Ruta(ruta2);

			int x = 1 + i;
			srv3.setTmioFechaInicio(LocalDate.of(2011, 05, x));
			srv3.setTmioFechaFin(LocalDate.of(2022, 03, x));

			Tmio1ServicioPK srvPK3 = new Tmio1ServicioPK();
			srvPK3.setCedulaConductor(srv3.getTmio1Conductore().getCedula());
			srvPK3.setIdBus(srv3.getTmio1Bus().getId());
			srvPK3.setIdRuta(srv3.getTmio1Ruta().getId());
			srvPK3.setFechaInicio(srv3.getTmioFechaInicio());
			srvPK3.setFechaFin(srv3.getTmioFechaFin());

			srv3.setId(srvPK3);
			srv3.setId_hash(srvPK3.getHashCode());

			servicioDao.save(srv3);
		}
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cantidadServiciosPorFecha() {
		assertNotNull(conductoresDao.cantidadServiciosPorFecha(LocalDate.of(2018, 2, 21)));
		assertEquals(conductoresDao.cantidadServiciosPorFecha(LocalDate.of(2018, 2, 21)).size(), 2);
		
		assertEquals(conductoresDao.cantidadServiciosPorFecha(LocalDate.of(2018, 2, 21)).get(0)[1],Long.parseLong("10"));
		assertEquals(((Tmio1Conductore) conductoresDao.cantidadServiciosPorFecha(LocalDate.of(2018, 2, 21)).get(0)[0]).getCedula(), "1111111111");

		assertEquals(conductoresDao.cantidadServiciosPorFecha(LocalDate.of(2018, 2, 21)).get(1)[1],Long.parseLong("1"));
		assertEquals(((Tmio1Conductore) conductoresDao.cantidadServiciosPorFecha(LocalDate.of(2018, 2, 21)).get(1)[0]).getCedula(), "0000000000");
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void rutasPorFechaConMaximo9Servicios() {
		assertNotNull(rutasDao.findRutasPorFechaConMaximo9Servicios(LocalDate.of(2018, 2, 21)));
		assertEquals(rutasDao.findRutasPorFechaConMaximo9Servicios(LocalDate.of(2018, 2, 21)).size(), 1);
		assertEquals("Street 70 until street 92",rutasDao.findRutasPorFechaConMaximo9Servicios(LocalDate.of(2018, 2, 21)).get(0).getDescripcion());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findBusesPorFechaConMasDe1Servicio() {
		assertNotNull(busesDao.findBusesPorFechaConMasDe1Servicio());
		assertEquals(busesDao.findBusesPorFechaConMasDe1Servicio().size(), 1);
		assertEquals("XYZ098", busesDao.findBusesPorFechaConMasDe1Servicio().get(0).getPlaca());
	}
}
