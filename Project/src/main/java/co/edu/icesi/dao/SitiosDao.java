package co.edu.icesi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.stream.Entity;

import co.edu.icesi.model.Tmio1Sitio;



@Repository
@Transactional
@Scope("singleton")
public class SitiosDao implements ISitiosDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void save(Tmio1Sitio entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Sitio entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Sitio entity) {
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));		
	}

	@Override
	public Tmio1Sitio findById(long id) {
		return entityManager.find(Tmio1Sitio.class, id);
	}

	@Override
	public List<Tmio1Sitio> findAll() {
		String jpql = "Select a from Tmio1Sitio a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
