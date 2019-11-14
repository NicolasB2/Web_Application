package co.edu.icesi.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;

@Repository
@Scope("singleton")
public class ServiciosDao implements IServiciosDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Servicio entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Servicio entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Servicio entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public Tmio1Servicio findById(Tmio1ServicioPK id) {
		return entityManager.find(Tmio1Servicio.class, id);
	}

	@Override
	public List<Tmio1Servicio> findAll() {
		String jpql = "Select a from Tmio1Servicio a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
