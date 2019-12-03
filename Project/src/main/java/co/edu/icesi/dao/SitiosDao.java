package co.edu.icesi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;
import co.edu.icesi.model.Tmio1Sitio;



@Repository
public class SitiosDao implements ISitiosDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	@Transactional
	public void save(Tmio1Sitio entity) {
		entityManager.persist(entity);
	}

	@Override
	@Transactional
	public void update(Tmio1Sitio entity) {
		entityManager.merge(entity);
	}

	@Override
	@Transactional
	public void delete(Tmio1Sitio entity) {
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));		
	}

	@Override
	@Transactional
	public Tmio1Sitio findById(long id) {
		return entityManager.find(Tmio1Sitio.class, id);
	}

	@Override
	@Transactional
	public List<Tmio1Sitio> findAll() {
		String jpql = "Select a from Tmio1Sitio a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
