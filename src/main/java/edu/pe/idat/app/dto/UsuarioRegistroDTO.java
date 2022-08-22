package edu.pe.idat.app.dto;

public class UsuarioRegistroDTO {

	private Integer id;

	private String nombre;

	private String apellidos;

	private int dni;

	private String email;

	private int telefono;

	private int edad;

	private String username;

	private String password;

	private String foto;

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

	public UsuarioRegistroDTO(Integer id, String nombre, String apellidos, int dni, String email, int telefono,
			int edad, String username, String password, String foto) {
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
	}

	public UsuarioRegistroDTO(String nombre, String apellidos, int dni, String email, int telefono, int edad,
			String username, String password, String foto) {
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
	}

	public UsuarioRegistroDTO() {
		super();
	}

	public UsuarioRegistroDTO(String username) {
		super();
		this.username = username;
	}

}
