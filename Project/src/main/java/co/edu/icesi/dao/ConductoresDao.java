package co.edu.icesi.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Conductore;

@Repository
@Transactional
@Scope("singleton")
public class ConductoresDao implements IConductoresDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Conductore entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Conductore entity) {
		entityManager.merge(entity);	
	}

	@Override
	public void delete(Tmio1Conductore entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		
	}

	@Override
	public Tmio1Conductore findById(String id) {
		return entityManager.find(Tmio1Conductore.class, id);
	}

	@Override
	public List<Tmio1Conductore> findAll() {
		String jpql = "Select a from Tmio1Conductore a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByNombre(String nombre) {
		String jpql = "Select a from Tmio1Conductore a where a.nombre = '"+ nombre + "'" ;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByApellido(String apellidos) {
		String jpql = "Select a from Tmio1Conductore a where a.apellidos = '" + apellidos+ "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Object[]> cantidadServiciosPorFecha(LocalDate date) {
		String jpql = "SELECT conductores, COUNT(servicios) "
				+ "FROM Tmio1Conductore conductores JOIN conductores.tmio1Servicios servicios "
				+ "WHERE servicios.id.fechaInicio <= :date "
				+ "AND servicios.id.fechaFin >= :date "
				+ "GROUP BY conductores ORDER BY conductores.fechaContratacion" ;
	
		return 	entityManager.createQuery(jpql).setParameter("date", date).getResultList();
	}
	
}
