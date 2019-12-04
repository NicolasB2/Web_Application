package co.edu.icesi.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the tmio1_sitios_rutas database table.
 * 
 */
@Entity
@Table(name="tmio1_sitios_rutas")
@NamedQuery(name="Tmio1SitiosRuta.findAll", query="SELECT t FROM Tmio1SitiosRuta t")
public class Tmio1SitiosRuta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tmio1SitiosRutaPK id;

	private Integer idruta;
	
	private Integer idsitio;
	
	private Integer id_hash;

	//bi-directional many-to-one association to Tmio1Ruta
	@ManyToOne
	@JoinColumn(name="id_ruta", insertable=false, updatable=false)
	private Tmio1Ruta tmio1Ruta1;

	

	//bi-directional many-to-one association to Tmio1Sitio
	@ManyToOne
	@JoinColumn(name="id_sitio", insertable=false, updatable=false)
	private Tmio1Sitio tmio1Sitio1;


	public Tmio1SitiosRuta() {
	}

	public Tmio1SitiosRutaPK getId() {
		return this.id;
	}

	public void setId(Tmio1SitiosRutaPK id) {
		this.id = id;
	}

	public Tmio1Ruta getTmio1Ruta1() {
		return this.tmio1Ruta1;
	}

	public void setTmio1Ruta1(Tmio1Ruta tmio1Ruta1) {
		this.tmio1Ruta1 = tmio1Ruta1;
	}


	public Tmio1Sitio getTmio1Sitio1() {
		return this.tmio1Sitio1;
	}

	public void setTmio1Sitio1(Tmio1Sitio tmio1Sitio1) {
		this.tmio1Sitio1 = tmio1Sitio1;
	}

	public Integer getId_hash() {
		return id_hash;
	}

	public void setId_hash(Integer id_hash) {
		this.id_hash = id_hash;
	}

	public Integer getIdruta() {
		return idruta;
	}

	public void setIdruta(Integer idruta) {
		this.idruta = idruta;
	}

	public Integer getIdsitio() {
		return idsitio;
	}

	public void setIdsitio(Integer idsitio) {
		this.idsitio = idsitio;
	}

	
	
}