package co.edu.icesi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.model.Tmio1ServiciosSitioPK;
import co.edu.icesi.model.Tmio1SitiosRutaPK;

@Repository
@Transactional
@Scope("singleton")
public class ServiciosSitioDao implements IServiciosSitioDao {

	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1ServiciosSitio entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1ServiciosSitio entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1ServiciosSitio entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));	
	}

	@Override
	public Tmio1ServiciosSitio findById(Tmio1ServiciosSitioPK id) {
		return entityManager.find(Tmio1ServiciosSitio.class, id);
	}

	@Override
	public List<Tmio1ServiciosSitio> findAll() {
		String jpql = "Select a from Tmio1ServiciosSitio a";
		return entityManager.createQuery(jpql).getResultList();
	
	};
	
	
}
