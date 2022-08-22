package edu.pe.idat.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	@Column(length = 30, nullable = false)
	private String nombre;
	@NotBlank
	@Column(length = 30, nullable = false)
	private String apellidos;
	@NotNull
	@Column(length = 8, nullable = false)
	private int dni;
	@NotBlank
	@Email
	@Column(length = 40, nullable = false)
	private String email;
	@Column(nullable = false)
	private int telefono;
	@NotNull
	@Column
	private int edad;
	
	private String foto;
	
	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipo equipo;
	

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Persona() {
		super();
	}

	public Persona(Integer id, @NotBlank String nombre, @NotBlank String apellidos, @NotNull int dni,
			@NotBlank @Email String email, int telefono, @NotNull int edad, String foto, Equipo equipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.edad = edad;
		this.foto = foto;
		this.equipo = equipo;
	}

	public Persona(@NotBlank String nombre, @NotBlank String apellidos, @NotNull int dni, @NotBlank @Email String email,
			int telefono, @NotNull int edad, String foto, Equipo equipo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.edad = edad;
		this.foto = foto;
		this.equipo = equipo;
	}

	public Persona(@NotBlank String nombre) {
		super();
		this.nombre = nombre;
	}
	
	

}
