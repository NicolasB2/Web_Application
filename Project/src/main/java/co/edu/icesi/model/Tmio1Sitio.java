package co.edu.icesi.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tmio1_sitios database table.
 * 
 */
@Entity
@Table(name="tmio1_sitios")
@NamedQuery(name="Tmio1Sitio.findAll", query="SELECT t FROM Tmio1Sitio t")
public class Tmio1Sitio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TMIO1_SITIOS_ID_GENERATOR", sequenceName="TMIO1_SITIOS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMIO1_SITIOS_ID_GENERATOR")
	private long id;

	@NotBlank
	private String descripcion;

	@NotBlank
	private String nombre;

	//bi-directional many-to-one association to Tmio1ServiciosSitio
	@OneToMany(mappedBy="tmio1Sitio")
	@JsonIgnore
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitios;

	//bi-directional many-to-one association to Tmio1SitiosRuta
	@OneToMany(mappedBy="tmio1Sitio1")
	@JsonIgnore
	private List<Tmio1SitiosRuta> tmio1SitiosRutas1;


	public Tmio1Sitio() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Tmio1ServiciosSitio> getTmio1ServiciosSitios() {
		return this.tmio1ServiciosSitios;
	}

	public void setTmio1ServiciosSitios(List<Tmio1ServiciosSitio> tmio1ServiciosSitios) {
		this.tmio1ServiciosSitios = tmio1ServiciosSitios;
	}

	public Tmio1ServiciosSitio addTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().add(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Sitio(this);

		return tmio1ServiciosSitio;
	}

	public Tmio1ServiciosSitio removeTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().remove(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Sitio(null);

		return tmio1ServiciosSitio;
	}

	public List<Tmio1SitiosRuta> getTmio1SitiosRutas1() {
		return this.tmio1SitiosRutas1;
	}

	public void setTmio1SitiosRutas1(List<Tmio1SitiosRuta> tmio1SitiosRutas1) {
		this.tmio1SitiosRutas1 = tmio1SitiosRutas1;
	}

	public Tmio1SitiosRuta addTmio1SitiosRutas1(Tmio1SitiosRuta tmio1SitiosRutas1) {
		getTmio1SitiosRutas1().add(tmio1SitiosRutas1);
		tmio1SitiosRutas1.setTmio1Sitio1(this);

		return tmio1SitiosRutas1;
	}

	public Tmio1SitiosRuta removeTmio1SitiosRutas1(Tmio1SitiosRuta tmio1SitiosRutas1) {
		getTmio1SitiosRutas1().remove(tmio1SitiosRutas1);
		tmio1SitiosRutas1.setTmio1Sitio1(null);

		return tmio1SitiosRutas1;
	}



}