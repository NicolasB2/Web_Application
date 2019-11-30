package co.edu.icesi.delegate;

import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;

public interface IDelegateBus {

	
	public Iterable<Tmio1Bus> getTmioBuses();

	public Tmio1Bus addTmioBus(Tmio1Bus newTmioBus);

	public void delTmioBus(Tmio1Bus newTmioBus);

	public Tmio1Bus getTmioBus(int i);

	public BusType[] getType();
}
