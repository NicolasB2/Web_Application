package co.edu.icesi.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dao.BusesDao;
import co.edu.icesi.exceptions.BusNullException;
import co.edu.icesi.exceptions.BusTipoException;
import co.edu.icesi.exceptions.CapacidadNullException;
import co.edu.icesi.exceptions.GuardarBusException;
import co.edu.icesi.exceptions.TipoNullException;
import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;

@Service
public class BusService {

	@Autowired
	private BusesDao repository;

	public Tmio1Bus save(Tmio1Bus bus) throws Exception {

		if (bus == null) {
			throw new BusNullException();
		} else if (repository.findByPlaca(bus.getPlaca())!=null){
			throw new Exception();
		}else if (bus.getTipo() == null || bus.getTipo().equals("")) {
			throw new TipoNullException();
		} else if (bus.getCapacidad() == null) {
			throw new CapacidadNullException();
		} else if ((bus.getCapacidad().compareTo(new BigDecimal(0)) <= 0)) {
			throw new GuardarBusException();
		} else if (!(bus.getTipo().toString().equals("T") || bus.getTipo().toString().equals("P") || bus.getTipo().toString().equals("A"))) {
			throw new BusTipoException();
		} else {
			repository.save(bus);
			return bus;
		}
	}

	public void setRepository(BusesDao repository) {
		this.repository = repository;
	}
	
	public Iterable<Tmio1Bus> findAll(){
		return repository.findAll();
	}

	public BusType[] getTypes() {
		return BusType.values();
	}
	
	public Tmio1Bus findById(Integer id) {
		return repository.findById(id);
	}

}
