package co.edu.icesi.delegate;

import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Conductore;

public interface IDelegateConductor {

	
	public Iterable<Tmio1Conductore> getTmioConductores();

	public Tmio1Conductore addTmioConductor(Tmio1Conductore newTmioConductor);

	public void delTmioConductor(Tmio1Conductore newTmioConductor);

	public Tmio1Conductore getTmioCondutor(int id);

	//public BusType[] getType();
	
}
