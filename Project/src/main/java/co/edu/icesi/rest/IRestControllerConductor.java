package co.edu.icesi.rest;

import co.edu.icesi.model.Tmio1Conductore;

public interface IRestControllerConductor {

	
	public Iterable<Tmio1Conductore> getConductores();
	
	public Tmio1Conductore getConductor (String cedula);
	
	public Tmio1Conductore addConductor(Tmio1Conductore conductor);
	
	public Tmio1Conductore delConductor(String cedula);
	
}
