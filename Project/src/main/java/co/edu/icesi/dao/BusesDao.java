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
import co.edu.icesi.model.Tmio1Bus;

@Repository
@Transactional
@Scope("singleton")
public class BusesDao implements IBusesDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Bus entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Bus entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Bus entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public Tmio1Bus findById(Integer id) {
		return entityManager.find(Tmio1Bus.class, id);
	}

	@Override
	public List<Tmio1Bus> findAll() {
		String jpql = "Select a from Tmio1Bus a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByPlaca(String placa) {
		String jpql = "Select a from Tmio1Bus a where a.placa = '" + placa + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByMarca(String marca) {
		String jpql = "Select a from Tmio1Bus a  where a.marca = '" + marca + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByModelo(BigDecimal modelo) {
		String jpql = "Select a from Tmio1Bus a  where a.marca = '" + modelo + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findBusesPorFechaConMasDe1Servicio() {
		String jpql = "SELECT DISTINCT busesA FROM Tmio1Bus busesA "
				+ "JOIN busesA.tmio1Servicios serviciosA, Tmio1Bus busesB JOIN busesB.tmio1Servicios serviciosB " 
				+ "WHERE busesA.id = busesB.id "
				+ "AND ((serviciosA.tmio1Ruta.diaInicio BETWEEN serviciosB.tmio1Ruta.diaInicio AND serviciosB.tmio1Ruta.diaFin) "
				+ "OR (serviciosA.tmio1Ruta.diaFin BETWEEN serviciosB.tmio1Ruta.diaInicio AND serviciosB.tmio1Ruta.diaFin)) "
				+ "AND serviciosA.id != serviciosB.id "
				+ "GROUP BY busesA "
				+ "HAVING COUNT(serviciosA) > 1";
		return entityManager.createQuery(jpql).getResultList();

	}

}
