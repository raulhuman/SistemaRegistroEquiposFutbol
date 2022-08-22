package edu.pe.idat.app.models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Usuario {

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
	@NotBlank
	private String username;
	@Column
	@NotBlank
	private String password;

	private String foto;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id" , referencedColumnName = "id")
			)
	private List<Rol> roles;
	
	@OneToOne(mappedBy = "usuario")
	private Equipo equipo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@PrePersist
	private void prePersist() {
		fechaCreacion = new Date();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> rols) {
		this.roles = rols;
	}
	

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Usuario(Integer id, @NotBlank String nombre, @NotBlank String apellidos, @NotNull int dni,
			@NotBlank @Email String email, int telefono, @NotNull int edad, @NotBlank String username,
			@NotBlank String password, String foto,  List<Rol> roles, Date fechaCreacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.edad = edad;
		this.username = username;
		this.password = password;
		this.foto = foto;
		this.roles = roles;
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario(Integer id) {
		super();
		this.id = id;
	}

	public Usuario() {
		super();
	}

	public Usuario(@NotBlank String nombre, @NotBlank String apellidos, @NotNull int dni, @NotBlank @Email String email,
			int telefono, @NotNull int edad, @NotBlank String username, @NotBlank String password, String foto,
			 List<Rol> roles) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.edad = edad;
		this.username = username;
		this.password = password;
		this.foto = foto;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", email="
				+ email + ", telefono=" + telefono + ", edad=" + edad + ", username=" + username + ", password="
				+ password + ", foto=" + foto + ", roles=" + roles + ", fechaCreacion=" + fechaCreacion + "]";
	}
	

}
