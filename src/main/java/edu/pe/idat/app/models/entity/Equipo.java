package edu.pe.idat.app.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
public class Equipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(length = 25, nullable = false)
	private String nombre;
	
	private String foto;
	

	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecRegistro;
	
	@PrePersist
	private void PrePersist() {
		fecRegistro = new Date();
	}
	
	@OneToOne
	@JoinColumn(name="representante_id", referencedColumnName = "id")
	private Usuario usuario;
	
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

	public Date getFecRegistro() {
		return fecRegistro;
	}

	public void setFecRegistro(Date fecRegistro) {
		this.fecRegistro = fecRegistro;
	}

	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Equipo() {
		super();
	}

	public Equipo(Integer id, @NotBlank String nombre, String foto, Date fecRegistro, Usuario usuario, Torneo torneo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.foto = foto;
		this.fecRegistro = fecRegistro;
		this.usuario = usuario;
		this.torneo = torneo;
	}

	public Equipo(@NotBlank String nombre, String foto, Date fecRegistro, Usuario usuario, Torneo torneo) {
		super();
		this.nombre = nombre;
		this.foto = foto;
		this.fecRegistro = fecRegistro;
		this.usuario = usuario;
		this.torneo = torneo;
	}

	public Equipo(@NotBlank String nombre) {
		super();
		this.nombre = nombre;
	}
	
	



	

}
