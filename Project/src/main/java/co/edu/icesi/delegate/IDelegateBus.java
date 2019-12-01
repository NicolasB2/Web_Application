package co.edu.icesi.delegate;

import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;

public interface IDelegateBus {

	
	public Iterable<Tmio1Bus> getTmioBuses();

	public void addTmioBus(Tmio1Bus newTmioBus) throws Exception;

	public void delTmioBus(int id);

	public Tmio1Bus getTmioBus(int i);

	public BusType[] getType();
}
