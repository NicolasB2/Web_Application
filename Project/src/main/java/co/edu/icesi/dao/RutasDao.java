package co.edu.icesi.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Ruta;

@Repository
@Transactional
@Scope("singleton")
public class RutasDao implements IRutasDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Ruta entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Ruta entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(Tmio1Ruta entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public Tmio1Ruta findById(Integer id) {
		return entityManager.find(Tmio1Ruta.class, id);
	}

	@Override
	public List<Tmio1Ruta> findAll() {
		String jpql = "Select a from Tmio1Ruta a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByRangoHoras(BigDecimal horaInicio, BigDecimal horaFin) {
		String jpql = "Select a from Tmio1Ruta a where a.horaInicio >="+ horaInicio + " AND "+ horaFin +" >= a.horaFin";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByRangoFechas(BigDecimal fechaInicio, BigDecimal fechaFin) {
		String jpql = "Select a from Tmio1Ruta a where a.diaInicio >="+ fechaInicio + " AND "+ fechaFin +" >= a.diaFin";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Tmio1Ruta> findRutasPorFechaConMaximo9Servicios(LocalDate date) {

		String jpql = ""
				+ "SELECT rutas "
				+ "FROM Tmio1Ruta rutas JOIN rutas.tmio1Servicios servicios "
				+ "WHERE servicios.id.fechaInicio <= :date "
				+ "AND servicios.id.fechaFin >= :date "
				+ "GROUP BY rutas " 
				+ "HAVING COUNT(servicios) < 10 ";
	
		return 	entityManager.createQuery(jpql).setParameter("date", date).getResultList();
	}

}
