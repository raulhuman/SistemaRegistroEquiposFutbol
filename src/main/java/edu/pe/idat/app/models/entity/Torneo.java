package edu.pe.idat.app.models.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
@Table(name = "torneo")
public class Torneo {

    @Id
    @Column(name = "id_torneo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	/*
	 * @Column(length = 15, nullable = false)
	 * 
	 * @NotBlank private String categoria;
	 */
    @Column(length = 150, nullable = false)
    @NotBlank
    private String descripcion;
    @Column(length = 15, nullable = false)
    @NotBlank
    private String estado;
    
    private String foto;
    
    @OneToMany
    @JoinColumn(name = "torneo_id")
    private List<Categoria> categorias = new ArrayList<>();
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @Column(nullable = false, name = "fecha_inicio")
    @NotNull
    private Date fechaInicio;
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}


	public Torneo(Integer id, @NotBlank String descripcion, @NotBlank String estado, String foto,
			List<Categoria> categorias, @NotNull Date fechaInicio) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
		this.foto = foto;
		this.categorias = categorias;
		this.fechaInicio = fechaInicio;
	}

	public Torneo(Integer id) {
		super();
		this.id = id;
	}

	public Torneo(@NotBlank String descripcion, @NotBlank String estado, String foto, List<Categoria> categorias,
			@NotNull Date fechaInicio) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		this.foto = foto;
		this.categorias = categorias;
		this.fechaInicio = fechaInicio;
	}

	public Torneo() {
		super();
	}

	

}