package edu.pe.idat.app.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	@Column(length = 30, nullable = false)
	private String nombre;
	@NotBlank
	@Column(length = 30, nullable = false)
	private String edadmaxima;
	@NotBlank
	@Column(length = 30, nullable = false)
	private String edadminima;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@PrePersist
	private void prePersist() {
		fechaInicio= new Date();
	}
	
	@ManyToOne
	@JoinColumn(name = "torneo_id")
	private Torneo torneo;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEdadmaxima() {
		return edadmaxima;
	}
	public void setEdadmaxima(String edadmaxima) {
		this.edadmaxima = edadmaxima;
	}
	public String getEdadminima() {
		return edadminima;
	}
	public void setEdadminima(String edadminima) {
		this.edadminima = edadminima;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Torneo getTorneo() {
		return torneo;
	}
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	
	public Categoria() {
		super();
	}
	public Categoria(Integer id, @NotBlank String nombre, @NotBlank String edadmaxima, @NotBlank String edadminima,
			Date fechaInicio, Torneo torneo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edadmaxima = edadmaxima;
		this.edadminima = edadminima;
		this.fechaInicio = fechaInicio;
		this.torneo = torneo;
	}
	public Categoria(@NotBlank String nombre, @NotBlank String edadmaxima, @NotBlank String edadminima,
			Date fechaInicio, Torneo torneo) {
		super();
		this.nombre = nombre;
		this.edadmaxima = edadmaxima;
		this.edadminima = edadminima;
		this.fechaInicio = fechaInicio;
		this.torneo = torneo;
	}
	@Override
	public String toString() {
		return nombre;
	}
	
	
	
}
