package co.edu.icesi.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import co.edu.icesi.model.Tmio1Ruta;

public interface IRutasDao{

	public void save(Tmio1Ruta entity);
	public void update(Tmio1Ruta entity);
	public void delete(Tmio1Ruta entity);
	public Tmio1Ruta findById(Integer id);
	public List<Tmio1Ruta> findAll();
	public List<Tmio1Ruta> findByRangoHoras(BigDecimal horaInicio, BigDecimal horaFin);
	public List<Tmio1Ruta> findByRangoFechas(BigDecimal fechaInicio, BigDecimal fechaFin);
	public List<Tmio1Ruta> findRutasPorFechaConMaximo9Servicios(LocalDate date);
}
