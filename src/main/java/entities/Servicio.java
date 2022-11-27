package entities;

public class Servicio {
	
	private int id;
	private String nombre, descripcion;
	
	public Servicio() {
		
	}
	
	public Servicio(Integer id) {
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		
		return "Servicio [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]\n";
	}
	
}
