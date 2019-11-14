package co.edu.icesi.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import co.edu.icesi.model.Tmio1Bus;

public interface IBusesDao  {

	public void save(Tmio1Bus entity);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
	public Tmio1Bus findById(Integer id);
	public List<Tmio1Bus> findAll();
	public List<Tmio1Bus> findByPlaca(String placa);
	public List<Tmio1Bus> findByMarca(String marca);
	public List<Tmio1Bus> findByModelo(BigDecimal modelo);
	public List<Tmio1Bus> findBusesPorFechaConMasDe1Servicio();
}
