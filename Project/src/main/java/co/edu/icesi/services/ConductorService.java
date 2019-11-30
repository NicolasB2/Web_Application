package co.edu.icesi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.ConductoresDao;
import co.edu.icesi.dao.IConductoresDao;
import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechaNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.FormatoIncorrectoException;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;

@Service
public class ConductorService {

	@Autowired
	private ConductoresDao repository;

	public Tmio1Conductore save(Tmio1Conductore conductor) throws ConductorNullException,
			FechasNoConsistentesException, FechaNullException, FormatoIncorrectoException {

		if (conductor == null) {
			throw new ConductorNullException();
		} else if (conductor.getFechaNacimiento() == null || conductor.getFechaContratacion() == null) {
			throw new FechaNullException();
		} else if (conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) == -1
				|| conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) == 0) {
			throw new FechasNoConsistentesException();
		} else {
			repository.save(conductor);
			return conductor;
		}
	}

	public void setRepository(ConductoresDao repository) {
		this.repository = repository;
	}

	public Iterable<Tmio1Conductore> findAll(){
		return repository.findAll();
	}
	
	public Tmio1Conductore findById(String cedula) {

		return repository.findById(cedula);
	}
	
	
	public Tmio1Conductore deleted(Tmio1Conductore conductor) {
		
		repository.delete(conductor);
		return conductor;
	}
}
