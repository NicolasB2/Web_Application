package co.edu.icesi.Daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
public class ServiciosDaoTest {

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
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Auidi");
		bus.setPlaca("KJB864");
		bus.setModelo(new BigDecimal("2019"));
		busesDao.save(bus);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2015,04,12));
		conductor.setFechaNacimiento(LocalDate.of(1999,03,29));

		conductor.setCedula("119876542782");
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz");
		conductoresDao.save(conductor);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 70 until street 92");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		rutasDao.save(ruta);

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

		servicioDao.save(srv);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveServicio() throws ParseException {

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Chevrolet");
		bus.setPlaca("DCS243");
		bus.setModelo(new BigDecimal("2019"));
		busesDao.save(bus);

		Tmio1Conductore conductor = new Tmio1Conductore();

		conductor.setFechaContratacion(LocalDate.of(2015,04,12));
		conductor.setFechaNacimiento(LocalDate.of(1999,03,29));

		conductor.setCedula("11511968728");
		conductor.setNombre("Daniela");
		conductor.setApellidos("Llano");
		conductoresDao.save(conductor);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 70 until street 92");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		rutasDao.save(ruta);

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

		servicioDao.save(srv);
		assertNotNull(servicioDao.findAll().get(0));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateServicio() throws ParseException {
		Tmio1Servicio servicio = servicioDao.findAll().get(0);

		servicio.setTmioFechaInicio(LocalDate.of(2017,03,12));
		servicio.setTmioFechaFin(LocalDate.of(2020,03,21));

		servicioDao.update(servicio);
		assertNotNull(servicioDao.findById(servicio.getId()));
		assertEquals(LocalDate.of(2020,03,21), servicioDao.findById(servicio.getId()).getTmioFechaFin());
		assertEquals(LocalDate.of(2017,03,12), servicioDao.findById(servicio.getId()).getTmioFechaInicio());

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteServicio() {
		Tmio1Servicio servicio = servicioDao.findAll().get(0);
		servicioDao.delete(servicio);
		assertNull(servicioDao.findById(servicio.getId()));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		Tmio1Servicio servicio = servicioDao.findAll().get(0);
		Tmio1ServicioPK servicioPK = servicio.getId();
		assertEquals(servicio, servicioDao.findById(servicioPK));
	}

}
