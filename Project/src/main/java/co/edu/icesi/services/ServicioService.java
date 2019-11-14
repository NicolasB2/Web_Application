package co.edu.icesi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.IServiciosDao;
import co.edu.icesi.dao.ServiciosDao;
import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.ConductorNullException;
import co.edu.icesi.exceptions.FechasNoConsistentesException;
import co.edu.icesi.exceptions.RutaNullException;
import co.edu.icesi.exceptions.ServicioNullException;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServicioPK;

@Service
public class ServicioService {

	@Autowired
	private ServiciosDao repository;

	public Tmio1Servicio save(Tmio1Servicio servicio) throws BusNullException, ConductorNullException,
			RutaNullException, FechasNoConsistentesException, ServicioNullException {

		if (servicio == null) {
			throw new ServicioNullException();
		}else if (servicio.getTmio1Bus() == null) {
			throw new BusNullException();
		} else if (servicio.getTmio1Conductore() == null) {
			throw new ConductorNullException();
		} else if (servicio.getTmio1Ruta() == null) {
			throw new RutaNullException();
		} else if (servicio.getTmioFechaInicio().compareTo(servicio.getTmioFechaFin()) == 1) {
			throw new FechasNoConsistentesException();
		} else if (servicio.getTmio1Conductore().getFechaContratacion().compareTo(servicio.getTmioFechaInicio()) == 1) {
			throw new FechasNoConsistentesException();
		} else {
			Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
			srvPK.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
			srvPK.setIdBus(servicio.getTmio1Bus().getId());
			srvPK.setIdRuta(servicio.getTmio1Ruta().getId());
			srvPK.setFechaInicio(servicio.getTmioFechaInicio());
			srvPK.setFechaFin(servicio.getTmioFechaFin());

			servicio.setId(srvPK);
			servicio.setId_hash(srvPK.getHashCode());
			
			repository.save(servicio);
			return  servicio;
		}
	}

	public Iterable<Tmio1Servicio> findAll() {
		return repository.findAll();
	}

	public Tmio1Servicio findById(Tmio1ServicioPK id) {
		return repository.findById(id);
	}

	public void delete(Tmio1Servicio servicio) {
		repository.delete(servicio);
	}

	public Iterable<Tmio1Servicio> filter(LocalDate inicio, LocalDate fin) {

		Iterable<Tmio1Servicio> lista = this.findAll();
		ArrayList<Tmio1Servicio> resultado = new ArrayList<>();
		for (Tmio1Servicio t : lista) {
			if (t.getTmioFechaInicio().compareTo(inicio) >= 0 && t.getTmioFechaFin().compareTo(fin) <= 0) {
				resultado.add(t);
			}
		}

		return resultado;
	}

}
