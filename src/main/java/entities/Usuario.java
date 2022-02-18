package entities;

import java.sql.Timestamp;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String password;
	private boolean habilitado;
	private Documento documento;
	private Timestamp fecha_creado;
	
	public Usuario() {
		
	}
	
	public Usuario(Integer id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return telefono;
	}
	public void setTel(String telefono) {
		this.telefono = telefono;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pw) {
		this.password = pw;
	}
	
	public Timestamp getFechaCreado() {
		return fecha_creado;
	}

	public void setFechaCreado(Timestamp fecha_creado) {
		this.fecha_creado = fecha_creado;
	}

	@Override
	public String toString() {
		return "\nUsuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", habilitado=" + habilitado
				+ ", created_at=" + fecha_creado + ", documento=" + documento + "]";
	}

}