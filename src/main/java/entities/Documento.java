package entities;

public class Documento {

	private String tipo;
	private String nro;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNro() {
		return nro;
	}
	public void setNro(String string) {
		this.nro = string;
	}
	@Override
	public String toString() {
		return "Documento [tipo=" + tipo + ", nro=" + nro + "]";
	}
	
	
}