package co.edu.icesi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Rollback(true)
public class BusesDaoTest {

	@Autowired
	private BusesDao busesDao;

	public void setUpBuses() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Mazda");
		bus.setPlaca("ABC000");
		bus.setModelo(new BigDecimal("2019"));
		busesDao.save(bus);
		
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(10));
		bus2.setTipo(BusType.T);
		bus2.setMarca("Mazda");
		bus2.setPlaca("XYZ321");
		bus2.setModelo(new BigDecimal("2019"));
		busesDao.save(bus2);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Mazda");
		bus.setPlaca("ABC000");
		bus.setModelo(new BigDecimal("2019"));
		busesDao.save(bus);
		assertNotNull(busesDao.findById(bus.getId()));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpDate() {
		setUpBuses();
		Tmio1Bus bus_aux = busesDao.findByPlaca("ABC000").get(0);
		bus_aux.setMarca("Chevrolet");
		bus_aux.setPlaca("AAA123");
		bus_aux.setModelo(new BigDecimal("2020"));

		busesDao.update(bus_aux);

		assertEquals("Chevrolet", busesDao.findByPlaca("AAA123").get(0).getMarca());
		assertEquals(new BigDecimal("2020"), busesDao.findByPlaca("AAA123").get(0).getModelo());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		setUpBuses();
		Tmio1Bus bus_aux = busesDao.findByPlaca("ABC000").get(0);
		busesDao.delete(bus_aux);
		assertTrue(busesDao.findByPlaca("ABC000").isEmpty());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByPlaca() {
		setUpBuses();
		assertEquals("ABC000", busesDao.findByPlaca("ABC000").get(0).getPlaca());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByMarca() {
		setUpBuses();
		assertEquals("Mazda", busesDao.findByMarca("Mazda").get(0).getMarca());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByModelo() {
		setUpBuses();
		assertNotNull(busesDao.findByModelo(new BigDecimal("2019")));
	}

}
