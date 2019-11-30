package co.edu.icesi.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.IRutasDao;
import co.edu.icesi.dao.RutasDao;
import co.edu.icesi.exceptions.FechaInvalidaException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.HoraInvalidaException;
import co.edu.icesi.exceptions.HoraNullException;
import co.edu.icesi.exceptions.HorasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Ruta;


@Service
public class RutaService {

	@Autowired
	private RutasDao repository;

	public Tmio1Ruta save(Tmio1Ruta ruta)
			throws RutaNullException, FechasNoConsistentesException, HorasNoConsistentesException, FechaNullException,
			HoraNullException, FechaInvalidaException, HoraInvalidaException {

		if (ruta == null) {
			throw new RutaNullException();
		} else if (ruta.getDiaInicio() == null || ruta.getDiaFin() == null) {
			throw new FechaNullException();
		} else if (ruta.getHoraInicio() == null || ruta.getHoraFin() == null) {
			throw new HoraNullException();
		} else if (ruta.getDiaInicio().compareTo(new BigDecimal(1)) == -1
				|| ruta.getDiaInicio().compareTo(new BigDecimal(7)) == 1
				|| ruta.getDiaFin().compareTo(new BigDecimal(1)) == -1
				|| ruta.getDiaFin().compareTo(new BigDecimal(7)) == 1) {
			throw new FechaInvalidaException();
		} else if (ruta.getHoraInicio().compareTo(new BigDecimal(0)) == -1
				|| ruta.getHoraInicio().compareTo(new BigDecimal(86399)) == 1
				|| ruta.getHoraFin().compareTo(new BigDecimal(0)) == -1
				|| ruta.getHoraFin().compareTo(new BigDecimal(86399)) == 1) {
			throw new HoraInvalidaException();
		} else if (ruta.getDiaFin().compareTo(ruta.getDiaInicio()) == -1) {
			throw new FechasNoConsistentesException();
		} else if (ruta.getHoraFin().compareTo(ruta.getHoraInicio()) == -1
				|| ruta.getHoraFin().compareTo(ruta.getHoraInicio()) == 0) {
			throw new HorasNoConsistentesException();
		} else {
			repository.save(ruta);
			return  ruta;
		}
	}

	public void setRepository(RutasDao repository) {
		this.repository = repository;
	}
	
	public Iterable<Tmio1Ruta> findAll(){
		return repository.findAll();
	}
	
	public Tmio1Ruta findById(Integer id) {

		return repository.findById(id);
	}
	
	public Tmio1Ruta deleted(Tmio1Ruta ruta) {
		repository.delete(ruta);
		return ruta;
	}
	
	
	
}
