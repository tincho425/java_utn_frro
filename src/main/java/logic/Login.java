package logic;

import entities.Persona;
import data.DataPersona;
import java.util.LinkedList;

public class Login {
	private DataPersona dp;
	
	public Login() {
		dp = new DataPersona();
	}
	
	public Persona validate(Persona p) {
		/* Para hacer más seguro el manejo de passwords este sería un lugar
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de las password en plano
		 */
		return dp.getByUser(p);
	}
	
	public LinkedList<Persona> getAll(String rol){
		return dp.getAll(rol);
	}
	
	public Persona getByDocumento(Persona per) {
		return dp.getByDocumento();
	}
}
