package co.edu.icesi.dao;

import java.time.LocalDate;
import java.util.List;

import co.edu.icesi.model.Tmio1Conductore;


public interface IConductoresDao {

	public void save(Tmio1Conductore entity);
	public void update(Tmio1Conductore entity);
	public void delete(Tmio1Conductore entity);
	public Tmio1Conductore findById(String id);
	public List<Tmio1Conductore> findAll();
	public List<Tmio1Conductore> findByNombre(String nombre);
	public List<Tmio1Conductore> findByApellido(String apellidos);
	public List<Object[]> cantidadServiciosPorFecha(LocalDate fecha);
}
