package co.edu.icesi.Daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import co.edu.icesi.dao.ConductoresDao;
import co.edu.icesi.model.Tmio1Conductore;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Rollback(true)
public class ConductoresDaoTest {

	@Autowired
	private ConductoresDao conductoresDao;


	@Before
	public void setUpConductores() throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2010,12,31));
		conductor.setFechaNacimiento(LocalDate.of(1990,12,31));
		conductor.setCedula("987654321");
		conductor.setNombre("Nicolas");
		conductor.setApellidos("Biojo");
		conductoresDao.save(conductor);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2010,12,31));
		conductor.setFechaNacimiento(LocalDate.of(1990,12,31));
		conductor.setCedula("123456789");
		conductor.setNombre("Nicolas");
		conductor.setApellidos("Biojo");
		conductoresDao.save(conductor);
		assertNotNull(conductoresDao.findById(conductor.getCedula()));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testNotSave() throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2010,12,31));
		conductor.setFechaNacimiento(LocalDate.of(1990,12,31));
		conductor.setCedula("987654321");
		conductor.setNombre("Nicolas");
		conductor.setApellidos("Biojo");
		
		try {
			conductoresDao.save(conductor);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2010,12,31));
		conductor.setFechaNacimiento(LocalDate.of(1990,12,31));
		conductor.setCedula("123123123");
		conductor.setNombre("-");
		conductor.setApellidos("-");
		conductoresDao.save(conductor);

		Tmio1Conductore conductore_aux = conductoresDao.findById("123123123");
		conductore_aux.setNombre("Alejandra");
		conductore_aux.setApellidos("Bermeo");

		conductoresDao.update(conductore_aux);

		assertEquals("Alejandra", conductoresDao.findById("123123123").getNombre());
		assertEquals("Bermeo", conductoresDao.findById("123123123").getApellidos());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		Tmio1Conductore conductore_aux = conductoresDao.findById("987654321");
		conductoresDao.delete(conductore_aux);
		assertNull(conductoresDao.findById("987654321"));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNombre() throws ParseException {
		assertNotNull(conductoresDao.findByNombre("Nicolas").get(0));
		assertEquals("Nicolas", conductoresDao.findByNombre("Nicolas").get(0).getNombre());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByApellidos() throws ParseException {
		assertNotNull(conductoresDao.findByApellido("Biojo").get(0));
		assertEquals("Biojo", conductoresDao.findByApellido("Biojo").get(0).getApellidos());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCantidadServiciosPorFecha() {
		
	}

}
