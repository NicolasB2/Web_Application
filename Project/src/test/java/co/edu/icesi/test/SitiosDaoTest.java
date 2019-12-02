package co.edu.icesi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.Application;
import co.edu.icesi.dao.SitiosDao;
import co.edu.icesi.model.Tmio1Sitio;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Rollback(true)
public class SitiosDaoTest {

	@Autowired
	private SitiosDao sitioDao;
	
	public void setUpSitio() {
		
		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setNombre("Plaza");
		sitio.setDescripcion("lugar de buena clase");
		sitio.setId(3);
		sitioDao.save(sitio);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave(){
	
		
		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setId(3);
		sitio.setNombre("CAM");
		sitio.setDescripcion("lugar de buena clase");
		sitioDao.save(sitio);
		
		assertNotNull(sitioDao.findAll().get(0));
		//assertEquals("Plaza", sitioDao.findAll().get(0).getNombre());
		
	}
}
