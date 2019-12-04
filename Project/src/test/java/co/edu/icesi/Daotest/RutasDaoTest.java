package co.edu.icesi.Daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import co.edu.icesi.dao.RutasDao;
import co.edu.icesi.model.Tmio1Ruta;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Rollback(true)
public class RutasDaoTest {

	@Autowired
	private RutasDao rutasDao;

	public void setUpRutas() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Simon Bolivar");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		rutasDao.save(ruta);
		
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setNumero("2");
		ruta2.setActiva("yes");
		ruta2.setDescripcion("cañas");
		ruta2.setDiaInicio(new BigDecimal(4));
		ruta2.setDiaFin(new BigDecimal(6));
		ruta2.setHoraInicio(new BigDecimal(2));
		ruta2.setHoraFin(new BigDecimal(2100));
		rutasDao.save(ruta);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		setUpRutas();
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Simon Bolivar");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		rutasDao.save(ruta);
		assertNotNull(rutasDao.findById(ruta.getId()));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpDate() {
		setUpRutas();
		Tmio1Ruta ruta_aux = rutasDao.findAll().get(0);
		int id = ruta_aux.getId();
		ruta_aux.setNumero("3");
		ruta_aux.setActiva("No");
		ruta_aux.setDescripcion("pasoancho");

		rutasDao.update(ruta_aux);

		assertEquals("3", rutasDao.findById(id).getNumero());
		assertEquals("No", rutasDao.findById(id).getActiva());
		assertEquals("pasoancho", rutasDao.findById(id).getDescripcion());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		setUpRutas();
		Tmio1Ruta ruta_aux = rutasDao.findAll().get(0);
		int id = ruta_aux.getId();
		rutasDao.delete(ruta_aux);
		assertNull(rutasDao.findById(id));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByHoras() {
		setUpRutas();
		assertNotNull(rutasDao.findByRangoHoras(new BigDecimal(1), new BigDecimal(4000)));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDias() {
		setUpRutas();
		assertNotNull(rutasDao.findByRangoFechas(new BigDecimal(1), new BigDecimal(7)));
	}

}
