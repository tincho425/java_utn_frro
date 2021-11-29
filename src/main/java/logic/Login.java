package logic;

import entities.Usuario;
import data.DataUsuario;
import java.util.LinkedList;

public class Login {
	private DataUsuario du;
	
	public Login() {
		du = new DataUsuario();
	}
	
	public Usuario validate(Usuario u) {
		/* Para hacer m�s seguro el manejo de passwords este ser�a un lugar
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asim�trico como sha256 y utilizar el hash en lugar de las password en plano
		 */
		return du.getByUser(u);
	}
	
	public LinkedList<Usuario> getAll(String rol){
		return du.getAll(rol);
	}
	
	public Usuario getByDocumento(Usuario usu) {
		return du.getByDocumento(usu);
	}
}
